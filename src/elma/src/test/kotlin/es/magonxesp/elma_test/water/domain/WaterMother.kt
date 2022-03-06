package es.magonxesp.elma_test.water.domain

import es.magonxesp.elma.water.domain.Water
import es.magonxesp.elma_test.shared.domain.bottle.BottleIdMother
import es.magonxesp.elma_test.shared.domain.water.WaterIdMother

class WaterMother {
    companion object {
        fun random(): Water = Water(
            id = WaterIdMother.random(),
            step = WaterStepMother.random(),
            bottle = BottleIdMother.random()
        )
    }

    class WaterStepMother {
        companion object {
            fun random(): Water.WaterStep = Water.WaterStep.values().random()
        }
    }
}