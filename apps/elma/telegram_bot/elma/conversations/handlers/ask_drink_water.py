from typing import List
from telegram import Update, Bot, InlineKeyboardMarkup, InlineKeyboardButton
from telegram.ext import Handler, ConversationHandler, CallbackContext, CallbackQueryHandler
import elma.conversations.conversations as conversation
import elma.stickers as stickers
from elma.translations import translation
import elma.conversations.handlers.ask_drink_source as ask_drink_source


REPLY_YES = "yes"
REPLY_NO = "no"


def send_question(bot: Bot, chat_id: str):
    bot.send_message(
        chat_id=chat_id,
        text=translation("ask-drink-water"),
        reply_markup=InlineKeyboardMarkup(
            inline_keyboard=[
                [
                    InlineKeyboardButton(text=translation("yes"), callback_data=REPLY_YES),
                    InlineKeyboardButton(text=translation("no"), callback_data=REPLY_NO)
                ]
            ],
        )
    )


def yes_response_handle(update: Update, context: CallbackContext):
    ask_drink_source.send_question(context.bot, update.effective_chat.id, update.callback_query.from_user.id)
    return conversation.STATE_CHOOSING


def no_response_handle(update: Update, context: CallbackContext):
    context.bot.send_sticker(chat_id=update.effective_chat.id, sticker=stickers.ELMA_DISGUSTING)
    return ConversationHandler.END


response_handlers: List[Handler] = [
    CallbackQueryHandler(callback=yes_response_handle, pattern=f"^{REPLY_YES}$"),
    CallbackQueryHandler(callback=no_response_handle, pattern=f"^{REPLY_NO}$"),
]
