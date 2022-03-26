package es.magonxesp.elma.reminder.domain

sealed class ReminderException(override val message: String?) : Exception() {
    class NotFound(override val message: String?) : ReminderException(message = message)
}