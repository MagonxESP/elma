package es.magonxesp.elma_test.bottle.domain

import es.magonxesp.elma.bottle.domain.Bottle
import es.magonxesp.elma.shared.domain.date.DateUtils
import es.magonxesp.elma_test.shared.domain.bottle.BottleIdMother
import es.magonxesp.elma_test.shared.domain.user.UserIdMother
import java.util.Random

class BottleMother {
    companion object {
        fun random(): Bottle = Bottle(
            id = BottleIdMother.random(),
            owner = UserIdMother.random(),
            capacity = BottleCapacityMother.random(),
            started = BottleStartedDateMother.random(),
            finished = BottleFinishedDateMother.random(),
            current = BottleCurrentMother.random()
        )
    }

    class BottleCapacityMother {
        companion object {
            fun random(): Bottle.BottleCapacity = Bottle.BottleCapacity(Random().nextDouble())
        }
    }

    class BottleStartedDateMother {
        companion object {
            fun random(): Bottle.BottleStartedDate = Bottle.BottleStartedDate(DateUtils.now())
        }
    }

    class BottleFinishedDateMother {
        companion object {
            fun random(): Bottle.BottleFinishedDate = Bottle.BottleFinishedDate(DateUtils.now())
        }
    }

    class BottleCurrentMother {
        companion object {
            fun random(): Bottle.BottleCurrent = Bottle.BottleCurrent(Random().nextBoolean())
        }
    }
}