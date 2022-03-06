package es.magonxesp.elma_test.water.application.create

import es.magonxesp.elma.water.application.create.WaterCreator
import es.magonxesp.elma.water.domain.WaterRepository
import es.magonxesp.elma_test.water.WaterModuleUnitTestCase
import es.magonxesp.elma_test.water.domain.WaterMother
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class WaterCreatorTest : WaterModuleUnitTestCase() {

    @Test
    fun `should create water`() {
        val repository = mockk<WaterRepository>(relaxed = true)
        val creator = WaterCreator(repository)
        val water = WaterMother.random()

        val created = creator.create(water.id.value, water.step.value, water.bottle.value)

        verify { repository.save(water) }

        assertEquals(water, created)
    }

}