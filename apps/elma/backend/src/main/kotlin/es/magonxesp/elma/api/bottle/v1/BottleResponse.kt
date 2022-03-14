package es.magonxesp.elma.api.bottle.v1

data class BottleResponse(
    val id: String,
    val owner: String,
    val capacity: Double,
    val current: Boolean,
    val startDate: String,
    val finished: String?
)