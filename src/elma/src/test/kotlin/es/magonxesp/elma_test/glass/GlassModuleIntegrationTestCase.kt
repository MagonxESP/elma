package es.magonxesp.elma_test.glass

import es.magonxesp.elma.shared.infraestructure.hibernate.HibernateDatabaseSession
import es.magonxesp.elma.glass.domain.Glass
import es.magonxesp.elma.glass.infraestructure.persistence.hibernate.GlassEntity
import es.magonxesp.elma_test.glass.domain.GlassMother
import org.junit.jupiter.api.AfterEach

open class GlassModuleIntegrationTestCase {

    private val testObjects: MutableList<Glass> = mutableListOf()

    protected fun insert(Glass: Glass) {
        HibernateDatabaseSession().transaction {
            save(GlassEntity.fromAggregate(Glass))
        }

        testObjects.add(Glass)
    }

    protected fun testGlass(insert: Boolean = true): Glass {
        return GlassMother.random().apply {
            if (insert) {
                insert(this)
            }
        }
    }

    protected fun testGlasses(count: Int): Array<Glass> {
        return mutableListOf<Glass>().apply {
            for (i in 1..count) {
                GlassMother.random().apply {
                    add(this)
                    insert(this)
                }
            }
        }.toTypedArray()
    }

    @AfterEach
    protected fun tearDown() {
        HibernateDatabaseSession().transaction {
            for (glass in testObjects) {
                remove(GlassEntity.fromAggregate(glass))
            }
        }
    }
    
}