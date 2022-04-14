package es.magonxesp.elma.api.water.v1

data class WaterRequest(
    val id: String,
    val step: Int,
    val bottleId: String
)
