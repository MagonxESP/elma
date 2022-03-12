import re
import uuid
from datetime import datetime
from telegram import Update, Bot
from telegram.ext import CallbackContext, RegexHandler
from elma.translations import translation
from elma.conversations.handlers.ask_drinked_amount import send_question
import elma.api.user as user_api
import elma.api.bottle as bottle_api

# Regex patterns
CAPACITY_REPLY_REGEX = "(\d+(,\d{1,2})?)(\ litros)?"

# states
STATE_CAPACITY = 1
STATE_DRINKED_AMOUNT = 2

# Callback query patterns
REPLY_IS_CURRENT = "is current"
REPLY_IS_NOT_CURRENT = "is not current"


def create_new_bottle(telegram_user_id: int, capacity: float, is_current: bool):
    user = user_api.get_user(telegram_user_id)
    return bottle_api.create_bottle(uuid.uuid4(), user["id"], capacity, is_current, datetime.now().strftime("%Y-%m-%d %H:%M:%S"))


def send_entry_point_question(bot: Bot, chat_id: str):
    bot.send_message(
        chat_id=chat_id,
        text=translation("ask-bottle-capacity")
    )


def bottle_capacity(update: Update, context: CallbackContext):
    reply_message = update.message.text
    match = re.match(CAPACITY_REPLY_REGEX, reply_message)

    if match is None:
        return STATE_CAPACITY

    capacity = float(match.group(1).replace(',', '.'))
    bottle = create_new_bottle(update.message.from_user.id, capacity, True)

    if bottle is False:
        context.bot.send_message(chat_id=update.effective_chat.id, text=translation("create_bottle_error"))
        return STATE_CAPACITY

    send_question(context.bot, update.effective_chat.id)

    return STATE_DRINKED_AMOUNT


response_handlers = [
    RegexHandler(CAPACITY_REPLY_REGEX, bottle_capacity)
]
