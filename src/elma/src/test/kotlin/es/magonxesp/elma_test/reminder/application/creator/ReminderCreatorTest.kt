package es.magonxesp.elma_test.reminder.application.creator

import es.magonxesp.elma.reminder.application.creator.ReminderCreator
import es.magonxesp.elma.reminder.domain.ReminderRepository
import es.magonxesp.elma_test.reminder.ReminderModuleUnitTestCase
import es.magonxesp.elma_test.reminder.domain.ReminderMother
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class ReminderCreatorTest : ReminderModuleUnitTestCase() {

    @Test
    fun `should create reminder`() {
        val repository = mockk<ReminderRepository>(relaxed = true)
        val creator = ReminderCreator(repository)
        val reminder = ReminderMother.random()

        val created = creator.create(reminder.id.value, reminder.toUser.value, reminder.scheduled.value, reminder.sended.value)

        verify {
            repository.save(reminder)
        }

        assertEquals(reminder, created)
    }

}