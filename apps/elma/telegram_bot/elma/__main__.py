from telegram.ext import CallbackContext, MessageHandler, Filters
import telegram.ext
from elma.handlers import add_handlers
import elma.settings as settings


telegram_bot_updater = telegram.ext.Updater(token=settings.TELEGRAM_BOT_TOKEN)


def main():
    add_handlers(telegram_bot_updater.dispatcher)

    telegram_bot_updater.start_polling()
    telegram_bot_updater.idle()


if __name__ == "__main__":
    main()
