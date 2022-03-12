package es.magonxesp.elma.api.requests.v1.bottle


data class CreateRequest(
    val id: String,
    val owner: String,
    val capacity: Double,
    val current: Boolean,
    val startDate: String
)
