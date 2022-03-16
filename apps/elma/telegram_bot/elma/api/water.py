import uuid
import json
from elma.api.client import request


def create_water(waterId: uuid.UUID, step: int, bottleId: uuid.UUID):
    response = request("POST", "/api/v1/water/create", {
        "id": str(waterId),
        "step": step,
        "bottleId": str(bottleId)
    })

    if response.status_code == 201:
        return json.loads(response.content)

    return False
