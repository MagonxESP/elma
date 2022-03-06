package es.magonxesp.elma.user.domain

import es.magonxesp.elma.shared.domain.user.UserId

interface UserRepository {
    fun all(): Array<User>
    fun find(id: UserId): User
    fun findByTelegramId(telegramId: User.UserTelegramId): User
    fun save(user: User): Unit
}