package es.magonxesp.elma.reminder.application.finder

import es.magonxesp.elma.reminder.domain.Reminder
import es.magonxesp.elma.reminder.domain.ReminderRepository
import java.util.*

class ReminderFinder(private val repository: ReminderRepository) {

    fun find(id: UUID): Reminder {
        return repository.find(Reminder.ReminderId(id))
    }

    fun betweenScheduledDates(from: String, to: String): Array<Reminder> {
        return repository.findScheduledBetween(
            Reminder.ReminderScheduledDate(from),
            Reminder.ReminderScheduledDate(to)
        )
    }

}