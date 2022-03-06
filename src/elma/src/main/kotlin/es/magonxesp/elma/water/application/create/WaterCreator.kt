package es.magonxesp.elma.water.application.create

import es.magonxesp.elma.water.domain.Water
import es.magonxesp.elma.water.domain.WaterRepository
import java.util.*

class WaterCreator(private val repository: WaterRepository) {

    fun create(id: UUID, step: Int, bottle: UUID): Water {
        val water = Water.fromPrimitives(id, step, bottle)

        repository.save(water)

        return water
    }

}