import uuid
from elma.api.client import request


def create_reminder(id: uuid.UUID, to_user: uuid.UUID, scheduled_date: str, sended_date: str = None):
    response = request("POST", "/api/v1/reminder/create", {
        "id": str(id),
        "toUser": str(to_user),
        "scheduledDate": scheduled_date,
        "sendedDate": sended_date
    })

    if response.status_code == 201:
        return True

    return False
