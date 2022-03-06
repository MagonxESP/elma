package es.magonxesp.elma.bottle.application.create

import es.magonxesp.elma.bottle.domain.Bottle
import es.magonxesp.elma.bottle.domain.BottleRepository
import java.util.UUID

class BottleCreator(private val repository: BottleRepository) {

    fun create(id: UUID, owner: UUID, capacity: Double, current: Boolean, startDate: String): Bottle {
        val bottle = Bottle.fromPrimitives(
            id = id,
            owner = owner,
            capacity = capacity,
            started = startDate,
            finished = null,
            current = current
        )

        repository.save(bottle)

        return bottle
    }

}