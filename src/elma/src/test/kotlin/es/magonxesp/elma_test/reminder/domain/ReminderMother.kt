package es.magonxesp.elma_test.reminder.domain

import es.magonxesp.elma.reminder.domain.Reminder
import es.magonxesp.elma.shared.domain.date.DateUtils
import es.magonxesp.elma_test.shared.domain.user.UserIdMother
import java.util.UUID

class ReminderMother {

    companion object {
        fun random(): Reminder = Reminder(
            id = ReminderIdMother.random(),
            toUser = UserIdMother.random(),
            scheduled = ReminderScheduledDateMother.random(),
            sended = ReminderSendedDateMother.random()
        )
    }

    class ReminderIdMother {
        companion object {
            fun random(): Reminder.ReminderId = Reminder.ReminderId(UUID.randomUUID())
        }
    }

    class ReminderScheduledDateMother {
        companion object {
            fun random(): Reminder.ReminderScheduledDate = Reminder.ReminderScheduledDate(DateUtils.now())
        }
    }

    class ReminderSendedDateMother {
        companion object {
            fun random(): Reminder.ReminderSendedDate = Reminder.ReminderSendedDate(null)
        }
    }

}
