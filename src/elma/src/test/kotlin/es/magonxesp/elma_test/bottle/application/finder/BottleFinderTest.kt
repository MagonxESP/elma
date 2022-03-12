package es.magonxesp.elma_test.bottle.application.finder

import es.magonxesp.elma.bottle.application.finder.BottleFinder
import es.magonxesp.elma.bottle.domain.BottleException
import es.magonxesp.elma.bottle.domain.BottleRepository
import es.magonxesp.elma_test.bottle.BottleModuleUnitTestCase
import es.magonxesp.elma_test.bottle.domain.BottleMother
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class BottleFinderTest : BottleModuleUnitTestCase() {

    @Test
    fun `should find bottle by id`() {
        val bottle = BottleMother.random()
        val repository = mockk<BottleRepository>()
        val finder = BottleFinder(repository)

        every { repository.find(bottle.id) } returns bottle

        val found = finder.find(bottle.id.value)

        verify { repository.find(bottle.id) }

        assertEquals(bottle, found)
    }

    @Test
    fun `should not find bottle by id`() {
        val bottle = BottleMother.random()
        val repository = mockk<BottleRepository>()
        val finder = BottleFinder(repository)

        every { repository.find(bottle.id) } throws BottleException.NotFound("Bottle with id ${bottle.id.value} not found")

        assertThrows<BottleException.NotFound> {
            finder.find(bottle.id.value)
        }
    }

}