package es.magonxesp.elma.water.domain

sealed class WaterException(override val message: String?) : Exception() {
    class NotFound(override val message: String?) : WaterException(message)
}
