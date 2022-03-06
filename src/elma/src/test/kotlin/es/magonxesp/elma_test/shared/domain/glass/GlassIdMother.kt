package es.magonxesp.elma_test.shared.domain.glass

import es.magonxesp.elma.shared.domain.glass.GlassId
import java.util.UUID

class GlassIdMother {
    companion object {
        fun random(): GlassId = GlassId(UUID.randomUUID())
    }
}
