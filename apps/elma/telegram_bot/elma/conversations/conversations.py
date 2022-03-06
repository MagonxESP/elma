from telegram.ext import ConversationHandler
import elma.conversations.handlers.ask_drink_water as ask_drink_water
import elma.conversations.handlers.ask_drink_source as ask_drink_source
import elma.conversations.handlers.new_bottle as new_bottle
import elma.conversations.handlers.ask_drinked_amount as ask_drinked_amount

# Generic states
STATE_END = 0
STATE_CHOOSING = 1
STATE_TYPING = 2


new_bottle_conversation = ConversationHandler(
    entry_points=new_bottle.response_handlers,
    states={
        new_bottle.STATE_CAPACITY: new_bottle.response_handlers,
        new_bottle.STATE_DRINKED_AMOUNT: ask_drinked_amount.response_handlers
    },
    fallbacks=[],
    persistent=False,
    allow_reentry=True
)

ask_drink_source_conversation = ConversationHandler(
    entry_points=ask_drink_water.response_handlers,
    states={
        STATE_CHOOSING: ask_drink_source.response_handlers,
        ask_drink_source.STATE_MY_BOTTLE: ask_drinked_amount.response_handlers,
        ask_drink_source.STATE_NEW_BOTTLE: [new_bottle_conversation]
    },
    fallbacks=[],
    persistent=False,
    allow_reentry=True
)

conversations = [
    ask_drink_source_conversation
]
