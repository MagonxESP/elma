package es.magonxesp.elma_test.bottle.application.create

import es.magonxesp.elma.bottle.application.create.BottleCreator
import es.magonxesp.elma.bottle.domain.Bottle
import es.magonxesp.elma.bottle.domain.BottleRepository
import es.magonxesp.elma_test.bottle.BottleModuleUnitTestCase
import es.magonxesp.elma_test.bottle.domain.BottleMother
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class BottleCreatorTest : BottleModuleUnitTestCase() {

    @Test
    fun `should create new bottle`() {
        val repository = mockk<BottleRepository>(relaxed = true)
        val creator = BottleCreator(repository)
        val bottle = BottleMother.random()
        bottle.finished = Bottle.BottleFinishedDate(null)

        val created = creator.create(
            id = bottle.id.value,
            owner = bottle.owner.value,
            capacity = bottle.capacity.value,
            current = bottle.current.value,
            startDate = bottle.started.value
        )

        verify { repository.save(bottle) }

        assertEquals(bottle, created)
    }

}