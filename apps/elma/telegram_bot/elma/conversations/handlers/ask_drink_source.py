import uuid

from telegram import InlineKeyboardMarkup, InlineKeyboardButton, Update, Bot
from telegram.ext import CallbackQueryHandler, CallbackContext, ConversationHandler
from elma.conversations.handlers.new_bottle import send_entry_point_question
from elma.translations import translation
from elma.conversations.handlers import ask_drinked_amount
import elma.responses as responses
import elma.api.user as user_api
import elma.api.bottle as bottle_api
import elma.api.glass as glass_api


MY_BOTTLE = "my bottle"
NEW_BOTTLE = "new bottle"
GLASS = "on glass"

# Ask drink source states
STATE_MY_BOTTLE = 3
STATE_NEW_BOTTLE = 4
STATE_GLASS = 5


def reply_my_bottle(update: Update, context: CallbackContext):
    ask_drinked_amount.send_question(context.bot, update.effective_chat.id)
    return STATE_MY_BOTTLE


def reply_new_bottle(update: Update, context: CallbackContext):
    send_entry_point_question(context.bot, update.effective_chat.id)
    return STATE_NEW_BOTTLE


def reply_glass(update: Update, context: CallbackContext):
    user = user_api.get_user(update.callback_query.from_user.id)

    if user is False:
        context.bot.send_message(chat_id=update.effective_chat.id, text=translation("get_user_error"))
        return ConversationHandler.END

    if glass_api.create_glass(uuid.uuid4(), user["id"]):
        responses.send_ok_sticker(context.bot, update.effective_chat.id)
    else:
        context.bot.send_message(chat_id=update.effective_chat.id, text=translation("create_glass_error"))

    return ConversationHandler.END


response_handlers = [
    CallbackQueryHandler(callback=reply_my_bottle, pattern=f"^{MY_BOTTLE}$"),
    CallbackQueryHandler(callback=reply_new_bottle, pattern=f"^{NEW_BOTTLE}$"),
    CallbackQueryHandler(callback=reply_glass, pattern=f"^{GLASS}$")
]


def send_question(bot: Bot, chat_id: str, telegram_user_id: int):
    keyboard = [
        [InlineKeyboardButton(text=translation("a-bottle"), callback_data=NEW_BOTTLE)],
        [InlineKeyboardButton(text=translation("glass"), callback_data=GLASS)]
    ]

    user = user_api.get_user(telegram_user_id)

    if user is False:
        bot.send_message(chat_id=chat_id, text=translation("get_user_error"))
        return

    current_bottle = bottle_api.get_current_bottle(user["id"])

    if current_bottle != bottle_api.BOTTLE_NOT_FOUND:
        keyboard.insert(0, [InlineKeyboardButton(text=translation("my-bottle"), callback_data=MY_BOTTLE)])
        keyboard[1][0] = InlineKeyboardButton(text=translation("new-bottle"), callback_data=NEW_BOTTLE)

    bot.send_message(
        chat_id=chat_id,
        text=translation("ask-drink-source"),
        reply_markup=InlineKeyboardMarkup(
            inline_keyboard=keyboard,
        )
    )
