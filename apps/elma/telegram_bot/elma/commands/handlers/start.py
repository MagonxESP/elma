from telegram.ext import CallbackContext, CommandHandler
from telegram import Update
import elma.conversations.conversations as conversation
from elma.translations import translation
from elma.conversations.handlers.ask_drink_water import send_question


def start(update: Update, context: CallbackContext):
    # TODO call user create on the backend

    context.bot.send_message(
        chat_id=update.effective_chat.id,
        text=translation("start")
    )

    send_question(context.bot, update.effective_chat.id)

    return conversation.STATE_CHOOSING


start_command_handler = CommandHandler("start", start)
