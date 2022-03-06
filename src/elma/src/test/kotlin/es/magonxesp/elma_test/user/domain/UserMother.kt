package es.magonxesp.elma_test.user.domain

import es.magonxesp.elma.user.domain.User
import es.magonxesp.elma_test.shared.domain.user.UserIdMother
import kotlin.random.Random

class UserMother {
    companion object {
        fun random(): User = User(
            id = UserIdMother.random(),
            telegramUserId = UserTelegramIdMother.random()
        )
    }

    class UserTelegramIdMother {
        companion object {
            fun random(): User.UserTelegramId = User.UserTelegramId(Random.nextInt())
        }
    }
}