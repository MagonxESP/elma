import os.path
from dotenv import load_dotenv

ROOT_DIRECTORY = os.path.abspath(os.path.join(os.path.basename(__file__), '..'))
ENV_FILE = os.path.join(ROOT_DIRECTORY, ".env")

if os.path.exists(ENV_FILE):
    load_dotenv(ENV_FILE)

# API
ELMA_BACKEND_API_BASE_URL = os.getenv("ELMA_BACKEND_API_BASE_URL")

# Telegram Bot
TELEGRAM_BOT_TOKEN = os.getenv("TELEGRAM_BOT_TOKEN")

# Rabbitmq
# RABBITMQ_HOST = os.getenv("RABBITMQ_HOST")
# RABBITMQ_PORT = os.getenv("RABBITMQ_PORT")
# RABBITMQ_USERNAME = os.getenv("RABBITMQ_USERNAME")
# RABBITMQ_PASSWORD = os.getenv("RABBITMQ_PASSWORD")
