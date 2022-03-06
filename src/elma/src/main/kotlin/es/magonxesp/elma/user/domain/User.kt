package es.magonxesp.elma.user.domain

import es.magonxesp.elma.shared.domain.user.UserId
import java.util.UUID

data class User(
    val id: UserId,
    val telegramUserId: UserTelegramId
) {
    data class UserTelegramId(val value: Int)

    companion object {
        fun fromPrimitives(
            id: UUID,
            telegramUserId: Int
        ) = User(
            id = UserId(id),
            telegramUserId = UserTelegramId(telegramUserId)
        )
    }
}
