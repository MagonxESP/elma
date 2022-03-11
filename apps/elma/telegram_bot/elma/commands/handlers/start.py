import uuid

from telegram.ext import CallbackContext, CommandHandler
from telegram import Update
import elma.conversations.conversations as conversation
from elma.translations import translation
from elma.conversations.handlers.ask_drink_water import send_question
import elma.api.user as user_api


def start(update: Update, context: CallbackContext):
    if user_api.create_user(uuid.uuid4(), update.message.from_user.id) is False:
        context.bot.send_message(
            chat_id=update.effective_chat.id,
            text=translation("create_user_error")
        )

        return

    context.bot.send_message(
        chat_id=update.effective_chat.id,
        text=translation("start")
    )

    send_question(context.bot, update.effective_chat.id)

    return conversation.STATE_CHOOSING


start_command_handler = CommandHandler("start", start)
