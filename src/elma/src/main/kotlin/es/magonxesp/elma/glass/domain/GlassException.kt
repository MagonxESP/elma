package es.magonxesp.elma.glass.domain

sealed class GlassException(override val message: String?) : Exception() {
    class NotFound(override val message: String?) : GlassException(message)
}
