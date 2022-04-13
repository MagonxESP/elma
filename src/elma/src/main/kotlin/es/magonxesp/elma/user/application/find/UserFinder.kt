package es.magonxesp.elma.user.application.find

import es.magonxesp.elma.shared.domain.user.UserId
import es.magonxesp.elma.user.domain.User
import es.magonxesp.elma.user.domain.UserRepository
import java.util.*

class UserFinder(private val repository: UserRepository) {

    fun all(): Array<User> {
        return repository.all();
    }

    fun findByTelegramUserId(telegramUserId: Int): User {
        return repository.findByTelegramId(User.UserTelegramId(telegramUserId))
    }

    fun find(id: UUID): User {
        return repository.find(UserId(id))
    }

}