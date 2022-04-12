package es.magonxesp.elma_test.reminder.application.update

import es.magonxesp.elma.reminder.application.update.ReminderUpdater
import es.magonxesp.elma.reminder.domain.ReminderRepository
import es.magonxesp.elma_test.reminder.ReminderModuleUnitTestCase
import es.magonxesp.elma_test.reminder.domain.ReminderMother
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class ReminderUpdaterTest : ReminderModuleUnitTestCase() {

    @Test
    fun `should update existent reminder values`() {
        val repository = mockk<ReminderRepository>(relaxed = true)
        val updater = ReminderUpdater(repository)
        val reminder = ReminderMother.random()

        updater.update(reminder)

        verify {
            repository.save(reminder)
        }
    }

}