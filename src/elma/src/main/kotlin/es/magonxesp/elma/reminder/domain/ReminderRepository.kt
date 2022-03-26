package es.magonxesp.elma.reminder.domain

import es.magonxesp.elma.shared.domain.user.UserId

interface ReminderRepository {
    fun find(id: Reminder.ReminderId): Reminder
    fun findByUser(userId: UserId): Array<Reminder>
    fun save(reminder: Reminder)
}
