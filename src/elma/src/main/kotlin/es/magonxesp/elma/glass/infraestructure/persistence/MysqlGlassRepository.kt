package es.magonxesp.elma.glass.infraestructure.persistence

import es.magonxesp.elma.glass.domain.Glass
import es.magonxesp.elma.glass.domain.GlassException
import es.magonxesp.elma.glass.domain.GlassRepository
import es.magonxesp.elma.glass.infraestructure.persistence.hibernate.GlassEntity
import es.magonxesp.elma.shared.domain.glass.GlassId
import es.magonxesp.elma.shared.infraestructure.hibernate.HibernateDatabaseSession

class MysqlGlassRepository : GlassRepository {
    override fun find(id: GlassId): Glass {
        val result = HibernateDatabaseSession().query {
            session -> session.createQuery(
                "SELECT g FROM GlassEntity g WHERE id = :id",
                GlassEntity::class.java
            ).setParameter("id", id.value.toString())
        }

        if (result.isNullOrEmpty()) {
            throw GlassException.NotFound("Glass with id ${id.value} not found")
        }

        return (result.first() as GlassEntity).toAggregate()
    }

    override fun save(glass: Glass) {
        HibernateDatabaseSession().transaction {
            save(GlassEntity.fromAggregate(glass))
        }
    }
}