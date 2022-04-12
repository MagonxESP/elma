package es.magonxesp.elma.reminder.application.finder

import es.magonxesp.elma.reminder.domain.Reminder
import es.magonxesp.elma.reminder.domain.ReminderRepository

class ReminderFinder(private val repository: ReminderRepository) {

    fun betweenScheduledDates(from: String, to: String): Array<Reminder> {
        return repository.findScheduledBetween(
            Reminder.ReminderScheduledDate(from),
            Reminder.ReminderScheduledDate(to)
        )
    }

}