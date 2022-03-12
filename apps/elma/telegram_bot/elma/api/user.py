import uuid
import json
from elma.api.client import request


def create_user(_uuid: uuid.UUID, telegram_user_id: int):
    response = request("POST", "/api/v1/user/create", {
        "id": str(_uuid),
        "telegramUserId": telegram_user_id
    })

    if response.status_code == 200:
        return True

    return False


def get_user(telegram_user_id: int):
    response = request("GET", f"/api/v1/user/telegram-id/{telegram_user_id}")

    if response.status_code == 200:
        return json.loads(response.content)

    return False
