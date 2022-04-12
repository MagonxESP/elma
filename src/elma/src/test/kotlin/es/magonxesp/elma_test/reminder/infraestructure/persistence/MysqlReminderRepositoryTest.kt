package es.magonxesp.elma_test.reminder.infraestructure.persistence

import es.magonxesp.elma.reminder.domain.Reminder
import es.magonxesp.elma.reminder.domain.ReminderException
import es.magonxesp.elma.reminder.infraestructure.persistence.MysqlReminderRepository
import es.magonxesp.elma_test.reminder.ReminderModuleIntegrationTestCase
import es.magonxesp.elma_test.shared.domain.user.UserIdMother
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MysqlReminderRepositoryTest : ReminderModuleIntegrationTestCase() {

    @Test
    fun `should find by id`() {
        val reminder = testReminder()
        val repository = MysqlReminderRepository()

        val found = repository.find(reminder.id)

        assertEquals(reminder, found)
    }

    @Test
    fun `should not find by id`() {
        val reminder = testReminder(false)
        val repository = MysqlReminderRepository()

        assertThrows<ReminderException.NotFound> {
            repository.find(reminder.id)
        }
    }

    @Test
    fun `should find by user`() {
        val reminder = testReminder()
        val repository = MysqlReminderRepository()

        val found = repository.findByUser(reminder.toUser)

        assertContentEquals(arrayOf(reminder), found)
    }

    @Test
    fun `should not find by user`() {
        val repository = MysqlReminderRepository()
        val found = repository.findByUser(UserIdMother.random())

        assertContentEquals(arrayOf(), found)
    }

    @Test
    fun `should find between scheduled dates`() {
        val reminder = testReminder(scheduled = "2022-04-11 00:00:00", sended = null)
        val repository = MysqlReminderRepository()
        val found = repository.findScheduledBetween(
            Reminder.ReminderScheduledDate("2022-04-10 00:00:00"),
            Reminder.ReminderScheduledDate("2022-04-12 00:00:00")
        )

        assertContains(found, reminder)
    }

    @Test
    fun `should save`() {
        val reminder = testReminder(false)
        val repository = MysqlReminderRepository()

        repository.save(reminder)
        val found = repository.find(reminder.id)

        assertEquals(reminder, found)
    }

}