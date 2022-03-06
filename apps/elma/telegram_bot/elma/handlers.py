from typing import List
from telegram.ext import Dispatcher, Handler
from elma.commands.commands import commands
from elma.conversations.conversations import conversations

# Telegram bot handlers
handlers: List[Handler] = []

handlers.extend(commands)
handlers.extend(conversations)


# Add handlers to the bot dispatcher
def add_handlers(dispatcher: Dispatcher):
    for handler in handlers:
        if isinstance(handler, Handler):
            dispatcher.add_handler(handler)
