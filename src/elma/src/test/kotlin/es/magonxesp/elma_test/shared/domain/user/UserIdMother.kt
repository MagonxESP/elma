package es.magonxesp.elma_test.shared.domain.user

import es.magonxesp.elma.shared.domain.user.UserId
import java.util.*

class UserIdMother {
    companion object {
        fun random(): UserId = UserId(UUID.randomUUID())
    }
}