import uuid
from telegram import Bot, InlineKeyboardButton, InlineKeyboardMarkup, Update
from telegram.ext import CallbackContext, CallbackQueryHandler, ConversationHandler
from elma import responses
from elma.translations import translation

REPLY_LESS_THAN_MIDDLE = "less than middle"
REPLY_MIDDLE = "middle"
REPLY_GREATER_THAN_MIDDLE = "greater than middle"


def send_question(bot: Bot, chat_id: str):
    bot.send_message(
        chat_id=chat_id,
        text=translation("ask-drinked-amount"),
        reply_markup=InlineKeyboardMarkup(
            inline_keyboard=[
                [InlineKeyboardButton(text=translation("less-than-middle"), callback_data=REPLY_LESS_THAN_MIDDLE)],
                [InlineKeyboardButton(text=translation("middle"), callback_data=REPLY_MIDDLE)],
                [InlineKeyboardButton(text=translation("more-than-middle"), callback_data=REPLY_GREATER_THAN_MIDDLE)],
            ],
        )
    )


def save_drinked_step(step: int, telegram_user_id: int):
    # TODO save drinked water step on the backend
    pass


def reply_drinked_less_than_middle(update: Update, context: CallbackContext):
    # save_drinked_step(WaterStep.STEP_LESS_THAN_MIDDLE, update.callback_query.from_user.id)
    responses.send_ok_sticker(context.bot, update.callback_query.from_user.id)
    return ConversationHandler.END


def reply_drinked_middle(update: Update, context: CallbackContext):
    # save_drinked_step(WaterStep.STEP_MIDDLE, update.callback_query.from_user.id)
    responses.send_ok_sticker(context.bot, update.callback_query.from_user.id)
    return ConversationHandler.END


def reply_drinked_greater_than_middle(update: Update, context: CallbackContext):
    # save_drinked_step(WaterStep.STEP_LESS_THAN_MIDDLE, update.callback_query.from_user.id)
    responses.send_ok_sticker(context.bot, update.callback_query.from_user.id)
    return ConversationHandler.END


response_handlers = [
    CallbackQueryHandler(reply_drinked_less_than_middle, pattern=f"^{REPLY_LESS_THAN_MIDDLE}$"),
    CallbackQueryHandler(reply_drinked_middle, pattern=f"^{REPLY_MIDDLE}$"),
    CallbackQueryHandler(reply_drinked_greater_than_middle, pattern=f"^{REPLY_GREATER_THAN_MIDDLE}$"),
]