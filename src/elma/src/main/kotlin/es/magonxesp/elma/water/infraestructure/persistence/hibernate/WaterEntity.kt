package es.magonxesp.elma.water.infraestructure.persistence.hibernate

import es.magonxesp.elma.water.domain.Water
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "water")
open class WaterEntity {

    @Id
    @Column(name = "id", nullable = true, length = 36)
    open var id: String? = null

    @Column(name = "step", nullable = true)
    open var step: Int? = null

    @Column(name = "bottle", nullable = true, length = 36)
    open var bottle: String? = null

    companion object {
        fun fromAggregate(water: Water) = WaterEntity().apply {
            id = water.id.value.toString()
            step = water.step.value
            bottle = water.bottle.value.toString()
        }
    }

    fun toAggregate(): Water = Water.fromPrimitives(
        id = UUID.fromString(id!!),
        step = step!!,
        bottle = UUID.fromString(bottle!!)
    )

}