package es.magonxesp.elma.water.domain

import es.magonxesp.elma.shared.domain.bottle.BottleId
import es.magonxesp.elma.shared.domain.water.WaterId

interface WaterRepository {
    fun find(id: WaterId): Water
    fun findByBottle(bottleId: BottleId): Array<Water>
    fun save(water: Water)
}