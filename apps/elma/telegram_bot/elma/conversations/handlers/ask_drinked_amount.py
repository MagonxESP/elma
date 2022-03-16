import uuid
from telegram import Bot, InlineKeyboardButton, InlineKeyboardMarkup, Update
from telegram.ext import CallbackContext, CallbackQueryHandler, ConversationHandler
from elma import responses
from elma.translations import translation
import elma.api.water as water_api
import elma.api.user as user_api
import elma.api.bottle as bottle_api


REPLY_LESS_THAN_MIDDLE = "less than middle"
REPLY_MIDDLE = "middle"
REPLY_GREATER_THAN_MIDDLE = "greater than middle"

WATER_STEP_LESS_THAN_MIDDLE = 1
WATER_STEP_MIDDLE = 2
WATER_STEP_GREATER_THAN_MIDDLE = 3


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
    user = user_api.get_user(telegram_user_id)

    if user is False:
        return False

    current_bottle = bottle_api.get_current_bottle(user["id"])

    if current_bottle == bottle_api.BOTTLE_NOT_FOUND or current_bottle is False:
        return False

    water_created = water_api.create_water(uuid.uuid4(), step, current_bottle["id"])

    if water_created is False:
        return False

    return True


def reply_drinked_less_than_middle(update: Update, context: CallbackContext):
    if save_drinked_step(WATER_STEP_LESS_THAN_MIDDLE, update.callback_query.from_user.id):
        responses.send_ok_sticker(context.bot, update.callback_query.from_user.id)
    else:
        context.bot.send_message(chat_id=update.callback_query.from_user.id, text=translation("create_water_step_error"))

    return ConversationHandler.END


def reply_drinked_middle(update: Update, context: CallbackContext):
    if save_drinked_step(WATER_STEP_MIDDLE, update.callback_query.from_user.id):
        responses.send_ok_sticker(context.bot, update.callback_query.from_user.id)
    else:
        context.bot.send_message(chat_id=update.callback_query.from_user.id, text=translation("create_water_step_error"))

    return ConversationHandler.END


def reply_drinked_greater_than_middle(update: Update, context: CallbackContext):
    if save_drinked_step(WATER_STEP_GREATER_THAN_MIDDLE, update.callback_query.from_user.id):
        responses.send_ok_sticker(context.bot, update.callback_query.from_user.id)
    else:
        context.bot.send_message(chat_id=update.callback_query.from_user.id, text=translation("create_water_step_error"))

    return ConversationHandler.END


response_handlers = [
    CallbackQueryHandler(reply_drinked_less_than_middle, pattern=f"^{REPLY_LESS_THAN_MIDDLE}$"),
    CallbackQueryHandler(reply_drinked_middle, pattern=f"^{REPLY_MIDDLE}$"),
    CallbackQueryHandler(reply_drinked_greater_than_middle, pattern=f"^{REPLY_GREATER_THAN_MIDDLE}$"),
]