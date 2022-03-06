from telegram import Bot
import elma.stickers as stickers


def send_ok_sticker(bot: Bot, chat_id: int):
    bot.send_sticker(chat_id=chat_id, sticker=stickers.TOHRU_OK)
