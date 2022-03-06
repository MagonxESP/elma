package es.magonxesp.elma.bottle.application.current

import es.magonxesp.elma.bottle.domain.Bottle
import es.magonxesp.elma.bottle.domain.BottleRepository
import es.magonxesp.elma.shared.domain.user.UserId

class CurrentBottle(private val repository: BottleRepository) {

    fun setCurrent(bottle: Bottle) {
        val bottles = repository.findUserBottles(bottle.owner)

        for (_bottle in bottles) {
            if (_bottle.id != bottle.id) {
                _bottle.current = Bottle.BottleCurrent(false)
                repository.save(_bottle)
            }
        }

        bottle.current = Bottle.BottleCurrent(true)
        repository.save(bottle)
    }

    fun getCurrent(user: UserId): Bottle {
        return repository.findCurrentBottle(user)
    }

}