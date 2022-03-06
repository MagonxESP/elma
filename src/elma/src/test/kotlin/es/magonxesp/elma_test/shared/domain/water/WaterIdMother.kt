package es.magonxesp.elma_test.shared.domain.water

import es.magonxesp.elma.shared.domain.water.WaterId
import java.util.*

class WaterIdMother {
    companion object {
        fun random(): WaterId = WaterId(UUID.randomUUID())
    }
}