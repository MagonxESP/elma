package es.magonxesp.elma_test.water.infraestructure

import es.magonxesp.elma.water.domain.WaterException
import es.magonxesp.elma.water.infraestructure.persistence.MysqlWaterRepository
import es.magonxesp.elma_test.shared.domain.bottle.BottleIdMother
import es.magonxesp.elma_test.shared.domain.water.WaterIdMother
import es.magonxesp.elma_test.water.WaterModuleIntegrationTestCase
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MysqlWaterRepositoryTest : WaterModuleIntegrationTestCase() {

    @Test
    fun `should find water by id`() {
        val repository = MysqlWaterRepository()
        val water = testWater()

        val found = repository.find(water.id)

        assertEquals(water, found)
    }

    @Test
    fun `should not find water by id`() {
        val repository = MysqlWaterRepository()

        assertThrows<WaterException.NotFound> {
            repository.find(WaterIdMother.random())
        }
    }

    @Test
    fun `should find water by bottle id`() {
        val repository = MysqlWaterRepository()
        val water = testWater()

        val found = repository.findByBottle(water.bottle)

        assertContentEquals(arrayOf(water), found)
    }

    @Test
    fun `should not find water by bottle id`() {
        val repository = MysqlWaterRepository()
        val found = repository.findByBottle(BottleIdMother.random())

        assertContentEquals(arrayOf(), found)
    }

    @Test
    fun `should save`() {
        val water = testWater(false)
        val repository = MysqlWaterRepository()

        repository.save(water)
        val created = repository.find(water.id)

        assertEquals(water, created)
    }

}
