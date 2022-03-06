package es.magonxesp.elma_test.shared.domain

import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.faker

class Random {
    companion object {
        fun random(): Faker {
            return faker {  }
        }
    }
}