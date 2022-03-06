package es.magonxesp.elma_test.user

import es.magonxesp.elma.shared.infraestructure.hibernate.HibernateDatabaseSession
import es.magonxesp.elma.user.domain.User
import es.magonxesp.elma.user.infraestructure.persistence.hibernate.UserEntity
import es.magonxesp.elma_test.user.domain.UserMother
import org.junit.jupiter.api.AfterEach

open class UserModuleIntegrationTestCase {
    private val testObjects: MutableList<User> = mutableListOf()

    protected fun testUser(insert: Boolean = true): User {
        return UserMother.random().apply {
            val user = this
            testObjects.add(user)

            if (insert) {
                HibernateDatabaseSession().transaction {
                    save(UserEntity.fromAggregate(user))
                }
            }
        }
    }

    protected fun testUsers(count: Int): Array<User> {
        return mutableListOf<User>().apply {
            HibernateDatabaseSession().transaction {
                for (i in 1..count) {
                    UserMother.random().apply {
                        add(this)
                        save(UserEntity.fromAggregate(this))
                    }
                }
            }

            testObjects.addAll(this)
        }.toTypedArray()
    }

    @AfterEach
    protected fun tearDown() {
        HibernateDatabaseSession().transaction {
            for (user in testObjects) {
                remove(UserEntity.fromAggregate(user))
            }
        }
    }

}