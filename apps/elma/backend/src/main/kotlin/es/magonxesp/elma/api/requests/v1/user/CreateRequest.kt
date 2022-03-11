package es.magonxesp.elma.api.requests.v1.user

data class CreateRequest(
    val id: String,
    val telegramUserId: Int
)
