package es.magonxesp.elma.api.requests.v1.bottle

data class BottleResponse(
    val id: String,
    val owner: String,
    val capacity: Double,
    val current: Boolean,
    val startDate: String,
    val finished: String?
)