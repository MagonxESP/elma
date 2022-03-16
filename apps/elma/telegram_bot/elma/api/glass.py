import uuid
import json
from elma.api.client import request


def create_glass(glassId: uuid.UUID, userId: uuid.UUID):
    response = request("POST", "/api/v1/glass/create", {
        "id": str(glassId),
        "userId": str(userId)
    })

    if response.status_code == 201:
        return json.loads(response.content)

    return False
