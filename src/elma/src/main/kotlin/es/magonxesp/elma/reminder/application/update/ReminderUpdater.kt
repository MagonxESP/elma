package es.magonxesp.elma.reminder.application.update

import es.magonxesp.elma.reminder.domain.Reminder
import es.magonxesp.elma.reminder.domain.ReminderRepository

class ReminderUpdater(private val repository: ReminderRepository) {

    fun update(reminder: Reminder) {
        repository.save(reminder)
    }

}