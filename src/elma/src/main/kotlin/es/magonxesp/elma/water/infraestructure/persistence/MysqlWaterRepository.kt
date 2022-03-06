package es.magonxesp.elma.water.infraestructure.persistence

import es.magonxesp.elma.shared.domain.bottle.BottleId
import es.magonxesp.elma.shared.domain.water.WaterId
import es.magonxesp.elma.shared.infraestructure.hibernate.HibernateDatabaseSession
import es.magonxesp.elma.water.domain.Water
import es.magonxesp.elma.water.domain.WaterException
import es.magonxesp.elma.water.domain.WaterRepository
import es.magonxesp.elma.water.infraestructure.persistence.hibernate.WaterEntity

class MysqlWaterRepository : WaterRepository {
    override fun find(id: WaterId): Water {
        val result = HibernateDatabaseSession().query {
            session -> session.createQuery(
                "SELECT w FROM WaterEntity w WHERE id = :id"
            ).setParameter("id", id.value.toString())
        }

        if (result.isNullOrEmpty()) {
            throw WaterException.NotFound("Water with id ${id.value} not found")
        }

        return (result.first() as WaterEntity).toAggregate()
    }

    override fun findByBottle(bottleId: BottleId): Array<Water> {
        val result = HibernateDatabaseSession().query {
            session -> session.createQuery(
                "SELECT w FROM WaterEntity w WHERE bottle = :id"
            ).setParameter("id", bottleId.value.toString())
        }

        if (result.isNullOrEmpty()) {
            return arrayOf()
        }

        return result.map {
            (it as WaterEntity).toAggregate()
        }.toTypedArray()
    }

    override fun save(water: Water) {
        HibernateDatabaseSession().transaction {
            save(WaterEntity.fromAggregate(water))
        }
    }
}