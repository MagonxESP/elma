package es.magonxesp.elma_test.reminder

import es.magonxesp.elma.reminder.domain.Reminder
import es.magonxesp.elma.reminder.infraestructure.persistence.hibernate.ReminderEntity
import es.magonxesp.elma.shared.infraestructure.hibernate.HibernateDatabaseSession
import es.magonxesp.elma_test.reminder.domain.ReminderMother
import org.junit.jupiter.api.AfterEach

open class ReminderModuleIntegrationTestCase {
    private val testObjects: MutableList<Reminder> = mutableListOf()

    protected fun testReminder(insert: Boolean = true): Reminder {
        return ReminderMother.random().apply {
            val reminder = this
            testObjects.add(reminder)

            if (insert) {
                HibernateDatabaseSession().transaction {
                    save(ReminderEntity.fromAggregate(reminder))
                }
            }
        }
    }

    protected fun testReminders(count: Int): Array<Reminder> {
        return mutableListOf<Reminder>().apply {
            HibernateDatabaseSession().transaction {
                for (i in 1..count) {
                    ReminderMother.random().apply {
                        add(this)
                        save(ReminderEntity.fromAggregate(this))
                    }
                }
            }

            testObjects.addAll(this)
        }.toTypedArray()
    }

    @AfterEach
    protected fun tearDown() {
        HibernateDatabaseSession().transaction {
            for (reminder in testObjects) {
                remove(ReminderEntity.fromAggregate(reminder))
            }
        }
    }
}