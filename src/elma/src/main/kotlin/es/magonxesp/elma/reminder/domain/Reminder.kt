package es.magonxesp.elma.reminder.domain

import es.magonxesp.elma.shared.domain.user.UserId
import es.magonxesp.elma.shared.domain.value_object.DateValueObject
import java.util.UUID

data class Reminder(
    val id: ReminderId,
    val toUser: UserId,
    val scheduled: ReminderScheduledDate,
    val sended: ReminderSendedDate
) {
    data class ReminderId(val value: UUID)

    data class ReminderScheduledDate(override val value: String) : DateValueObject(value = value) {
        init {
            toLocalDateTime()
        }
    }

    data class ReminderSendedDate(override val value: String?) : DateValueObject(value = value) {
        init {
            if (!value.isNullOrEmpty()) {
                toLocalDateTime()
            }
        }
    }

    companion object {
        fun fromPrimitives(id: UUID, toUser: UUID, scheduled: String, sended: String?): Reminder = Reminder(
            id = ReminderId(id),
            toUser = UserId(toUser),
            scheduled = ReminderScheduledDate(scheduled),
            sended = ReminderSendedDate(sended)
        )
    }
}
