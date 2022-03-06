package es.magonxesp.elma.user.domain

sealed class UserException(override val message: String?) : Exception() {
    class Invalid(message: String?) : UserException(message)
    class NotFound(message: String?) : UserException(message)
}
