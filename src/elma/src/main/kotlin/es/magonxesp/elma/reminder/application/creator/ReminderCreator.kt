package es.magonxesp.elma.reminder.application.creator

import es.magonxesp.elma.reminder.domain.Reminder
import es.magonxesp.elma.reminder.domain.ReminderRepository
import java.util.*

class ReminderCreator(private val repository: ReminderRepository) {

    fun create(id: UUID, toUser: UUID, scheduledDate: String, sendedDate: String?): Reminder {
        val reminder = Reminder.fromPrimitives(id, toUser, scheduledDate, sendedDate)

        repository.save(reminder)

        return reminder
    }

}
