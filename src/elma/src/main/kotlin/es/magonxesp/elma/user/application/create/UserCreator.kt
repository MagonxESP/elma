package es.magonxesp.elma.user.application.create

import es.magonxesp.elma.user.domain.User
import es.magonxesp.elma.user.domain.UserRepository
import java.util.UUID
import es.magonxesp.elma.user.domain.UserException

class UserCreator(private val respository: UserRepository) {

    fun createIfNotExistByTelegramId(userId: UUID, telegramUserId: Int) {
        val user = User.fromPrimitives(userId, telegramUserId)

        try {
            respository.findByTelegramId(user.telegramUserId)
        } catch (exception: UserException.NotFound) {
            respository.save(user)
        }
    }

}