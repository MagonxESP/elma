package es.magonxesp.elma_test.glass.domain

import es.magonxesp.elma.glass.domain.Glass
import es.magonxesp.elma_test.shared.domain.glass.GlassIdMother
import es.magonxesp.elma_test.shared.domain.user.UserIdMother

class GlassMother {
    companion object {
        fun random(): Glass = Glass(
            id = GlassIdMother.random(),
            user = UserIdMother.random()
        )
    }
}