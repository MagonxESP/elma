from telegram.ext import CallbackContext
import telegram.ext
import elma.settings as settings


telegram_bot_updater = telegram.ext.Updater(token=settings.TELEGRAM_BOT_TOKEN)


def main():
    telegram_bot_updater.start_polling()
    telegram_bot_updater.idle()


if __name__ == "__main__":
    main()
