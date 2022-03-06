package es.magonxesp.elma_test.glass.application.create

import es.magonxesp.elma.glass.application.create.GlassCreator
import es.magonxesp.elma.glass.domain.GlassRepository
import es.magonxesp.elma_test.glass.GlassModuleUnitTestCase
import es.magonxesp.elma_test.glass.domain.GlassMother
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals


class GlassCreatorTest : GlassModuleUnitTestCase() {

    @Test
    fun `should create new glass`() {
        val repository = mockk<GlassRepository>(relaxed = true)
        val creator = GlassCreator(repository)
        val glass = GlassMother.random()

        val created = creator.create(glass.id.value, glass.user.value)

        verify { repository.save(glass) }

        assertEquals(glass, created)
    }

}