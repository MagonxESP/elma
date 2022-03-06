package es.magonxesp.elma_test.user.infraestructure.persistence

import es.magonxesp.elma.user.domain.UserException
import es.magonxesp.elma.user.infraestructure.persistence.MysqlUserRepository
import es.magonxesp.elma_test.shared.domain.user.UserIdMother
import es.magonxesp.elma_test.user.UserModuleIntegrationTestCase
import es.magonxesp.elma_test.user.domain.UserMother
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals


class MysqlUserRepositoryTest : UserModuleIntegrationTestCase() {

    @Test
    fun `should find all expected users`() {
        val users = testUsers(5)
        val repository = MysqlUserRepository()

        val found = repository.all()

        for (user in users) {
            assertContains(found, user)
        }
    }

    @Test
    fun `should not find all users`() {
        val repository = MysqlUserRepository()
        val found = repository.all()

        assertContentEquals(arrayOf(), found)
    }

    @Test
    fun `should find user by id`() {
        val user = testUser()
        val repository = MysqlUserRepository()
        val found = repository.find(user.id)

        assertEquals(user, found)
    }

    @Test
    fun `should not find user by id`() {
        assertThrows<UserException.NotFound> {
            val repository = MysqlUserRepository()
            repository.find(UserIdMother.random())
        }
    }

    @Test
    fun `should find user by telegram user id`() {
        val user = testUser()
        val repository = MysqlUserRepository()
        val found = repository.findByTelegramId(user.telegramUserId)

        assertEquals(user, found)
    }

    @Test
    fun `should not find user by telegram user id`() {
        assertThrows<UserException.NotFound> {
            val repository = MysqlUserRepository()
            repository.findByTelegramId(UserMother.UserTelegramIdMother.random())
        }
    }

    @Test
    fun `should save`() {
        val user = testUser(false)
        val repository = MysqlUserRepository()

        repository.save(user)
        val found = repository.find(user.id)

        assertEquals(user, found)
    }

}