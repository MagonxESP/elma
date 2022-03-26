package es.magonxesp.elma_test.user.application.find

import es.magonxesp.elma.user.application.find.UserFinder
import es.magonxesp.elma.user.domain.User
import es.magonxesp.elma.user.domain.UserException
import es.magonxesp.elma.user.domain.UserRepository
import es.magonxesp.elma_test.user.UserModuleUnitTestCase
import es.magonxesp.elma_test.user.domain.UserMother
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class UserFinderTest : UserModuleUnitTestCase() {

    @Test
    fun `should find by telegram user id`() {
        val user = UserMother.random()
        val repository = mockk<UserRepository>()
        val finder = UserFinder(repository)

        every { repository.findByTelegramId(user.telegramUserId) } returns user

        val found = finder.findByTelegramUserId(user.telegramUserId.value)

        verify { repository.findByTelegramId(user.telegramUserId) }

        assertEquals(user, found)
    }

    @Test
    fun `should not find by telegram user id`() {
        val user = UserMother.random()
        val repository = mockk<UserRepository>()
        val finder = UserFinder(repository)

        every {
            repository.findByTelegramId(user.telegramUserId)
        } throws UserException.NotFound("User with telegram user id ${user.telegramUserId} not found")

        assertThrows<UserException.NotFound> {
            finder.findByTelegramUserId(user.telegramUserId.value)
        }

        verify { repository.findByTelegramId(user.telegramUserId) }
    }

    @Test
    fun `should find all users`() {
        val users = arrayOf(UserMother.random())
        val repository = mockk<UserRepository>()
        val finder = UserFinder(repository)

        every { repository.all() } returns users

        val found = finder.all()

        verify { repository.all() }

        assertContentEquals(users, found)
    }

    @Test
    fun `should not find all users`() {
        val users = arrayOf<User>()
        val repository = mockk<UserRepository>()
        val finder = UserFinder(repository)

        every { repository.all() } returns users

        val found = finder.all()

        verify { repository.all() }

        assertContentEquals(users, found)
    }
}
