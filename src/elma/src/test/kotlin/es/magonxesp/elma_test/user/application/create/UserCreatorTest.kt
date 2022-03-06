package es.magonxesp.elma_test.user.application.create

import es.magonxesp.elma.user.application.create.UserCreator
import es.magonxesp.elma.user.domain.UserException
import es.magonxesp.elma.user.domain.UserRepository
import es.magonxesp.elma_test.user.UserModuleUnitTestCase
import es.magonxesp.elma_test.user.domain.UserMother
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class UserCreatorTest : UserModuleUnitTestCase() {

    @Test
    fun `should create if not exists`() {
        val user = UserMother.random()
        val repository = mockk<UserRepository>(relaxed = true)
        val creator = UserCreator(repository)

        every {
            repository.findByTelegramId(user.telegramUserId)
        } throws UserException.NotFound("User with telegram user id ${user.telegramUserId} not found")

        creator.createIfNotExistByTelegramId(user.id.value, user.telegramUserId.value)

        verify {
            repository.findByTelegramId(user.telegramUserId)
            repository.save(user)
        }
    }

    @Test
    fun `should not create if exists`() {
        val user = UserMother.random()
        val repository = mockk<UserRepository>()
        val creator = UserCreator(repository)

        every {
            repository.findByTelegramId(user.telegramUserId)
        } returns user

        creator.createIfNotExistByTelegramId(user.id.value, user.telegramUserId.value)

        verify { repository.findByTelegramId(user.telegramUserId) }
        verify(exactly = 0) { repository.save(user) }
    }

}