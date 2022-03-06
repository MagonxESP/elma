package es.magonxesp.elma_test.bottle

import es.magonxesp.elma.bottle.domain.Bottle
import es.magonxesp.elma.bottle.infraestructure.persistence.hibernate.BottleEntity
import es.magonxesp.elma.shared.infraestructure.hibernate.HibernateDatabaseSession
import es.magonxesp.elma_test.bottle.domain.BottleMother
import org.junit.jupiter.api.AfterEach

open class BottleModuleIntegrationTestCase {
    private val testObjects: MutableList<Bottle> = mutableListOf()

    protected fun insert(bottle: Bottle) {
        HibernateDatabaseSession().transaction {
            save(BottleEntity.fromAggregate(bottle))
        }

        testObjects.add(bottle)
    }

    protected fun testBottle(insert: Boolean = true): Bottle {
        return BottleMother.random().apply {
            if (insert) {
                insert(this)
            }
        }
    }

    protected fun testBottles(count: Int): Array<Bottle> {
        return mutableListOf<Bottle>().apply {
            for (i in 1..count) {
                BottleMother.random().apply {
                    add(this)
                    insert(this)
                }
            }
        }.toTypedArray()
    }

    @AfterEach
    protected fun tearDown() {
        HibernateDatabaseSession().transaction {
            for (bottle in testObjects) {
                remove(BottleEntity.fromAggregate(bottle))
            }
        }
    }
}