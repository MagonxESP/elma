package es.magonxesp.elma.bottle.infraestructure.persistence

import es.magonxesp.elma.bottle.domain.Bottle
import es.magonxesp.elma.bottle.domain.BottleException
import es.magonxesp.elma.bottle.domain.BottleRepository
import es.magonxesp.elma.bottle.infraestructure.persistence.hibernate.BottleEntity
import es.magonxesp.elma.shared.domain.bottle.BottleId
import es.magonxesp.elma.shared.domain.user.UserId
import es.magonxesp.elma.shared.infraestructure.hibernate.HibernateDatabaseSession

class MysqlBottleRepository : BottleRepository {

    override fun find(id: BottleId): Bottle {
        val result = HibernateDatabaseSession().query {
            session -> session.createQuery(
                "SELECT b FROM BottleEntity b WHERE id = :id",
                BottleEntity::class.java
            ).setParameter("id", id.value.toString())
        }

        if (result.isNullOrEmpty()) {
            throw BottleException.NotFound("Bottle with id ${id.value} not found")
        }

        return (result.first() as BottleEntity).toAggregate()
    }

    override fun findUserBottles(userId: UserId): Array<Bottle> {
        val result = HibernateDatabaseSession().query {
            session -> session.createQuery(
                "SELECT b FROM BottleEntity b WHERE owner = :id",
                BottleEntity::class.java
            ).setParameter("id", userId.value.toString())
        }

        if (result.isNullOrEmpty()) {
            return arrayOf()
        }

        return result.map {
            (it as BottleEntity).toAggregate()
        }.toTypedArray()
    }

    override fun findCurrentBottle(userId: UserId): Bottle {
        val result = HibernateDatabaseSession().query {
            session -> session.createQuery(
                "SELECT b FROM BottleEntity b WHERE owner = :id AND current = true",
                BottleEntity::class.java
            ).setParameter("id", userId.value.toString())
        }

        if (result.isNullOrEmpty()) {
            throw BottleException.NotFound("Current bottle for user with id ${userId.value} not found")
        }

        return (result.first() as BottleEntity).toAggregate()
    }

    override fun save(bottle: Bottle) {
        HibernateDatabaseSession().transaction {
            save(BottleEntity.fromAggregate(bottle))
        }
    }

}