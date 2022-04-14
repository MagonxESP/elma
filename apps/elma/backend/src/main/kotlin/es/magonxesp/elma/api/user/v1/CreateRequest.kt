package es.magonxesp.elma.api.user.v1

data class CreateRequest(
    val id: String,
    val telegramUserId: Int
)
