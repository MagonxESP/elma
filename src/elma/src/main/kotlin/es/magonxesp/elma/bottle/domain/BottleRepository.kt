package es.magonxesp.elma.bottle.domain

import es.magonxesp.elma.shared.domain.bottle.BottleId
import es.magonxesp.elma.shared.domain.user.UserId

interface BottleRepository {
    fun find(id: BottleId): Bottle
    fun findUserBottles(userId: UserId): Array<Bottle>
    fun findCurrentBottle(userId: UserId): Bottle
    fun save(bottle: Bottle)
}