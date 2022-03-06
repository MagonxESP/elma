package es.magonxesp.elma.glass.infraestructure.persistence.hibernate

import es.magonxesp.elma.glass.domain.Glass
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "glass")
open class GlassEntity {

    @Id
    @Column(name = "id", length = 36, nullable = true)
    open var id: String? = null

    @Column(name = "user", length = 36, nullable = true)
    open var user: String? = null

    companion object {
        fun fromAggregate(glass: Glass): GlassEntity = GlassEntity().apply {
            id = glass.id.value.toString()
            user = glass.user.value.toString()
        }
    }

    fun toAggregate(): Glass = Glass.fromPrimitives(
        id = UUID.fromString(id!!),
        user = UUID.fromString(user!!)
    )
}