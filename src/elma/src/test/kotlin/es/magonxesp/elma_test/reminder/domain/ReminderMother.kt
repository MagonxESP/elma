package es.magonxesp.elma_test.reminder.domain

import es.magonxesp.elma.reminder.domain.Reminder
import es.magonxesp.elma.shared.domain.date.DateUtils
import es.magonxesp.elma_test.shared.domain.user.UserIdMother
import java.util.UUID

class ReminderMother {

    companion object {
        fun random(id: UUID? = null, scheduled: String? = null, sended: String? = null): Reminder = Reminder(
            id = ReminderIdMother.random(id),
            toUser = UserIdMother.random(),
            scheduled = ReminderScheduledDateMother.random(scheduled),
            sended = ReminderSendedDateMother.random(sended)
        )
    }

    class ReminderIdMother {
        companion object {
            fun random(value: UUID? = null): Reminder.ReminderId = Reminder.ReminderId(value ?: UUID.randomUUID())
        }
    }

    class ReminderScheduledDateMother {
        companion object {
            fun random(value: String? = null): Reminder.ReminderScheduledDate = Reminder.ReminderScheduledDate(value ?: DateUtils.now())
        }
    }

    class ReminderSendedDateMother {
        companion object {
            fun random(value: String? = null): Reminder.ReminderSendedDate = Reminder.ReminderSendedDate(value)
        }
    }

}
