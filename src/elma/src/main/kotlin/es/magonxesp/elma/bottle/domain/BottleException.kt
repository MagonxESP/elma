package es.magonxesp.elma.bottle.domain

sealed class BottleException(override val message: String?) : Exception() {
    class NotFound(message: String?) : BottleException(message)
}
