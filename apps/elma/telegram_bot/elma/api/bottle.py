import uuid

from elma.api.client import request
import json


def create_bottle(id: uuid.UUID, owner: uuid.UUID, capacity: float, current: bool, start_date: str):
    response = request("POST", "/api/v1/bottle/create", {
        "id": str(id),
        "owner": str(owner),
        "capacity": capacity,
        "current": current,
        "startDate": start_date
    })

    if response.status_code == 201:
        return True

    return False


def get_bottle(bottle_id: uuid.UUID):
    response = request("GET", f"/api/v1/bottle/{str(bottle_id)}")

    if response.status_code == 200:
        return json.loads(response.content.decode("utf-8"))

    return False


def get_current_bottle(user_id: uuid.UUID):
    response = request("GET", "/api/v1/bottle/current", {
        "user": str(user_id)
    })

    if response.status_code == 200:
        return json.loads(response.content.decode("utf-8"))

    return False


def set_current_bottle(bottle_id: uuid.UUID):
    response = request("PUT", "/api/v1/bottle/current", {
        "bottle": str(bottle_id)
    })

    if response.status_code == 200:
        return json.loads(response.content.decode("utf-8"))

    return False
