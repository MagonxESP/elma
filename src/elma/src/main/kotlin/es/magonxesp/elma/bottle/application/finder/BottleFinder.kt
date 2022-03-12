package es.magonxesp.elma.bottle.application.finder

import es.magonxesp.elma.bottle.domain.Bottle
import es.magonxesp.elma.bottle.domain.BottleRepository
import es.magonxesp.elma.shared.domain.bottle.BottleId
import java.util.*

class BottleFinder(val repository: BottleRepository) {

    fun find(id: UUID): Bottle {
        return repository.find(BottleId(id))
    }

}