import elma.settings
import requests
import json
from requests import Response


def request(method: str, path: str, body: dict) -> Response:
    return requests.request(
        method,
        f"{elma.settings.ELMA_BACKEND_API_BASE_URL}{path}",
        data=json.dumps(body),
        headers={
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    )
