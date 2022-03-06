package es.magonxesp.elma.bottle.domain

import es.magonxesp.elma.shared.domain.bottle.BottleId
import es.magonxesp.elma.shared.domain.user.UserId
import es.magonxesp.elma.shared.domain.value_object.DateValueObject
import java.util.UUID

data class Bottle(
    val id: BottleId,
    val owner: UserId,
    val capacity: BottleCapacity,
    val started: BottleStartedDate,
    var finished: BottleFinishedDate,
    var current: BottleCurrent
) {
    data class BottleCapacity(val value: Double)
    data class BottleStartedDate(override val value: String) : DateValueObject(value = value)
    data class BottleFinishedDate(override var value: String?) : DateValueObject(value = value)
    data class BottleCurrent(val value: Boolean)

    companion object {
        fun fromPrimitives(
            id: UUID,
            owner: UUID,
            capacity: Double,
            started: String,
            finished: String?,
            current: Boolean
        ) = Bottle(
            id = BottleId(id),
            owner = UserId(owner),
            capacity = BottleCapacity(capacity),
            started = BottleStartedDate(started),
            finished = BottleFinishedDate(finished),
            current = BottleCurrent(current)
        )
    }
}
