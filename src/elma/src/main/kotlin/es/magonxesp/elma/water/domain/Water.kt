package es.magonxesp.elma.water.domain

import es.magonxesp.elma.shared.domain.bottle.BottleId
import es.magonxesp.elma.shared.domain.water.WaterId
import java.util.*

data class Water(
    val id: WaterId,
    val step: WaterStep,
    val bottle: BottleId
) {
    enum class WaterStep(val value: Int) {
        STEP_LESS_THAN_MIDDLE(1),
        STEP_MIDDLE(2),
        STEP_GREATER_THAN_MIDDLE(3);

        companion object {
            fun byValue(value: Int): WaterStep = values().first {
                it.value == value
            }
        }
    }

    companion object {
        fun fromPrimitives(id: UUID, step: Int, bottle: UUID): Water = Water(
            id = WaterId(id),
            step = WaterStep.byValue(step),
            bottle = BottleId(bottle)
        )
    }
}
