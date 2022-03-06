package es.magonxesp.elma_test.shared.domain.bottle

import es.magonxesp.elma.shared.domain.bottle.BottleId
import java.util.*

class BottleIdMother {
    companion object {
        fun random(): BottleId = BottleId(UUID.randomUUID())
    }
}