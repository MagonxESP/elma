package es.magonxesp.elma.api.reminder.v1

import es.magonxesp.elma.reminder.domain.Reminder
import kotlinx.serialization.Serializable

@Serializable
data class ReminderResponse(
    val id: String,
    val toUser: String,
    val scheduledDate: String,
    val sendedDate: String?
) {
    companion object {
        fun fromAggregate(reminder: Reminder) = ReminderResponse(
            id = reminder.id.value.toString(),
            toUser = reminder.toUser.value.toString(),
            scheduledDate = reminder.scheduled.value,
            sendedDate = reminder.sended.value
        )
    }
}
