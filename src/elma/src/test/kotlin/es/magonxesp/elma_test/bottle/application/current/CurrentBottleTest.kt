package es.magonxesp.elma_test.bottle.application.current

import es.magonxesp.elma.bottle.application.current.CurrentBottle
import es.magonxesp.elma.bottle.domain.Bottle
import es.magonxesp.elma.bottle.domain.BottleRepository
import es.magonxesp.elma_test.bottle.BottleModuleUnitTestCase
import es.magonxesp.elma_test.bottle.domain.BottleMother
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class CurrentBottleTest : BottleModuleUnitTestCase() {

    @Test
    fun `should set the bottle as current`() {
        val repository = mockk<BottleRepository>(relaxed = true)
        val current = CurrentBottle(repository)
        val bottle = BottleMother.random()

        every {
            repository.findUserBottles(bottle.owner)
        } returns arrayOf(bottle)

        current.setCurrent(bottle)

        verify { repository.findUserBottles(bottle.owner) }
        verify { repository.save(bottle) }
    }

    @Test
    fun `should get the user current bottle`() {
        val repository = mockk<BottleRepository>(relaxed = true)
        val current = CurrentBottle(repository)
        val bottle = BottleMother.random()

        bottle.current = Bottle.BottleCurrent(true)

        every {
            repository.findCurrentBottle(bottle.owner)
        } returns bottle

        val found = current.getCurrent(bottle.owner)

        assertEquals(bottle, found)
    }

}