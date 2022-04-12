package es.magonxesp.elma.reminder

import es.magonxesp.elma.reminder.application.creator.ReminderCreator
import es.magonxesp.elma.reminder.application.finder.ReminderFinder
import es.magonxesp.elma.reminder.infraestructure.persistence.MysqlReminderRepository

val repository = MysqlReminderRepository()

fun creator(): ReminderCreator = ReminderCreator(repository)
fun finder(): ReminderFinder = ReminderFinder(repository)
