package es.magonxesp.elma.api.http.user.v1

data class CreateRequest(
    val id: String,
    val telegramUserId: Int
)
