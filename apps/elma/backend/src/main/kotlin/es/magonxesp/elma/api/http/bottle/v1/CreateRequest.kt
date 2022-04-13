package es.magonxesp.elma.api.http.bottle.v1


data class CreateRequest(
    val id: String,
    val owner: String,
    val capacity: Double,
    val current: Boolean,
    val startDate: String
)
