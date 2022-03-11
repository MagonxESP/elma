import uuid

import elma.api.client as api


def create_user(_uuid: uuid.UUID, telegram_user_id: int):
    response = api.request('POST', '/api/v1/user/create', {
        'id': str(_uuid),
        'telegramUserId': telegram_user_id
    })

    if response.status_code == 200:
        return True

    return False
