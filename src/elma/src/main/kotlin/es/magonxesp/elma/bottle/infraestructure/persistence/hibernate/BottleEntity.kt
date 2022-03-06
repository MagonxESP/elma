package es.magonxesp.elma.bottle.infraestructure.persistence.hibernate

import es.magonxesp.elma.bottle.domain.Bottle
import java.util.UUID
import javax.persistence.Table
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Table(name = "bottle")
open class BottleEntity {

    @Id
    @Column(name = "id", nullable = true, length = 36)
    open var id: String? = null

    @Column(name = "owner", nullable = true, length = 36)
    open var owner: String? = null

    @Column(name = "capacity", nullable = true)
    open var capacity: Double? = null

    @Column(name = "started", nullable = true)
    open var started: String? = null

    @Column(name = "finished", nullable = true)
    open var finished: String? = null

    @Column(name = "current", nullable = true)
    open var current: Boolean? = null

    companion object {
        fun fromAggregate(bottle: Bottle): BottleEntity = BottleEntity().apply {
            id = bottle.id.value.toString()
            owner = bottle.owner.value.toString()
            capacity = bottle.capacity.value
            started = bottle.started.value
            finished = bottle.finished.value
            current = bottle.current.value
        }
    }

    fun toAggregate(): Bottle = Bottle.fromPrimitives(
        id = UUID.fromString(id!!),
        owner = UUID.fromString(owner!!),
        capacity = capacity!!,
        started = started!!,
        finished = finished,
        current = current!!
    )
}