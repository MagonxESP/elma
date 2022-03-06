package es.magonxesp.elma_test.glass.infraestructure.persistence

import es.magonxesp.elma.glass.domain.GlassException
import es.magonxesp.elma.glass.infraestructure.persistence.MysqlGlassRepository
import es.magonxesp.elma_test.glass.GlassModuleIntegrationTestCase
import es.magonxesp.elma_test.shared.domain.glass.GlassIdMother
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class MysqlGlassRepositoryTest : GlassModuleIntegrationTestCase() {

    @Test
    fun `should find by id`() {
        val repository = MysqlGlassRepository()
        val glass = testGlass()

        val found = repository.find(glass.id)

        assertEquals(glass, found)
    }

    @Test
    fun `should not find by id`() {
        val repository = MysqlGlassRepository()

        assertThrows<GlassException.NotFound> {
            repository.find(GlassIdMother.random())
        }
    }

    @Test
    fun `should save`() {
        val repository = MysqlGlassRepository()
        val glass = testGlass(false)

        repository.save(glass)
        val created = repository.find(glass.id)

        assertEquals(glass, created)
    }

}