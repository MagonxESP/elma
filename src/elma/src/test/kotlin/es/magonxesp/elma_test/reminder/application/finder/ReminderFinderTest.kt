package es.magonxesp.elma_test.reminder.application.finder

import es.magonxesp.elma.reminder.application.finder.ReminderFinder
import es.magonxesp.elma.reminder.domain.Reminder
import es.magonxesp.elma.reminder.domain.ReminderRepository
import es.magonxesp.elma_test.reminder.ReminderModuleUnitTestCase
import es.magonxesp.elma_test.reminder.domain.ReminderMother
import io.mockk.mockk
import io.mockk.every
import kotlin.test.Test
import kotlin.test.assertContains

class ReminderFinderTest : ReminderModuleUnitTestCase() {

    @Test
    fun `should find between dates`() {
        val from = Reminder.ReminderScheduledDate( "2022-04-10 00:00:00")
        val to = Reminder.ReminderScheduledDate("2022-04-12 00:00:00")
        val reminder = ReminderMother.random(scheduled = "2022-04-11 00:00:00", sended = null)
        val repository = mockk<ReminderRepository>()
        val finder = ReminderFinder(repository)

        every {
            repository.findScheduledBetween(from, to)
        } returns arrayOf(reminder)

        val found = finder.betweenScheduledDates(from.value, to.value)

        assertContains(found, reminder)
    }

}