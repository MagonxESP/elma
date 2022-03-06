package es.magonxesp.elma_test.water

import es.magonxesp.elma.shared.infraestructure.hibernate.HibernateDatabaseSession
import es.magonxesp.elma.water.domain.Water
import es.magonxesp.elma.water.infraestructure.persistence.hibernate.WaterEntity
import es.magonxesp.elma_test.water.domain.WaterMother
import org.junit.jupiter.api.AfterEach

open class WaterModuleIntegrationTestCase {
    private val testObjects: MutableList<Water> = mutableListOf()

    protected fun insert(water: Water) {
        HibernateDatabaseSession().transaction {
            save(WaterEntity.fromAggregate(water))
        }

        testObjects.add(water)
    }

    protected fun testWater(insert: Boolean = true): Water {
        return WaterMother.random().apply {
            if (insert) {
                insert(this)
            }
        }
    }

    protected fun testWaters(count: Int): Array<Water> {
        return mutableListOf<Water>().apply {
            for (i in 1..count) {
                WaterMother.random().apply {
                    add(this)
                    insert(this)
                }
            }
        }.toTypedArray()
    }

    @AfterEach
    protected fun tearDown() {
        HibernateDatabaseSession().transaction {
            for (water in testObjects) {
                remove(WaterEntity.fromAggregate(water))
            }
        }
    }
}