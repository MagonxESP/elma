package es.magonxesp.elma.api.reminder.v1

data class ReminderRequest(
    val id: String,
    val toUser: String,
    val scheduledDate: String,
    val sendedDate: String?
)
