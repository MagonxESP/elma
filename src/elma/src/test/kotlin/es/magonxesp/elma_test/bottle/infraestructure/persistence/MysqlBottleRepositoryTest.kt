package es.magonxesp.elma_test.bottle.infraestructure.persistence

import es.magonxesp.elma.bottle.domain.Bottle
import es.magonxesp.elma.bottle.domain.BottleException
import es.magonxesp.elma.bottle.infraestructure.persistence.MysqlBottleRepository
import es.magonxesp.elma_test.bottle.BottleModuleIntegrationTestCase
import es.magonxesp.elma_test.shared.domain.user.UserIdMother
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MysqlBottleRepositoryTest : BottleModuleIntegrationTestCase() {

    @Test
    fun `should find bottle by id`() {
        val bottle = testBottle()
        val repository = MysqlBottleRepository()

        val found = repository.find(bottle.id)

        assertEquals(bottle, found)
    }

    @Test
    fun `should not find bottle by id`() {
        val bottle = testBottle(false)
        val repository = MysqlBottleRepository()

        assertThrows<BottleException.NotFound> {
            repository.find(bottle.id)
        }
    }

    @Test
    fun `should find user bottles`() {
        val bottle = testBottle()
        val repository = MysqlBottleRepository()
        val found = repository.findUserBottles(bottle.owner)

        assertContentEquals(arrayOf(bottle), found)
    }

    @Test
    fun `should not find user bottles`() {
        val repository = MysqlBottleRepository()
        val found = repository.findUserBottles(UserIdMother.random())

        assertContentEquals(arrayOf(), found)
    }

    @Test
    fun `should find current user bottle`() {
        val bottle = testBottle(false)
        val repository = MysqlBottleRepository()

        bottle.current = Bottle.BottleCurrent(true)
        insert(bottle)

        val found = repository.findCurrentBottle(bottle.owner)

        assertEquals(bottle, found)
    }

    @Test
    fun `should not find current user bottle`() {
        val repository = MysqlBottleRepository()

        assertThrows<BottleException.NotFound> {
            repository.findCurrentBottle(UserIdMother.random())
        }
    }

    @Test
    fun `should save`() {
        val bottle = testBottle(false)
        val repository = MysqlBottleRepository()

        repository.save(bottle)
        val found = repository.find(bottle.id)

        assertEquals(bottle, found)
    }

}