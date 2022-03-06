package es.magonxesp.elma.glass.domain

import es.magonxesp.elma.shared.domain.glass.GlassId
import es.magonxesp.elma.shared.domain.user.UserId
import java.util.UUID

data class Glass(
    val id: GlassId,
    val user: UserId
) {
    companion object {
        fun fromPrimitives(id: UUID, user: UUID): Glass = Glass(
            id = GlassId(id),
            user = UserId(user)
        )
    }
}
