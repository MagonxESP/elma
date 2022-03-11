package es.magonxesp.elma.api.controller.v1.user

import es.magonxesp.elma.api.requests.v1.user.UserResponse
import es.magonxesp.elma.user.domain.UserException
import es.magonxesp.elma.user.finder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/user")
class UserGetController {

    @GetMapping("/telegram-id/{id}")
    @ResponseBody
    fun findByTelegramId(@PathVariable id: Int): UserResponse {
        try {
            return finder().findByTelegramUserId(id).let {
                UserResponse(it.id.value.toString(), it.telegramUserId.value)
            }
        } catch (exception: UserException.NotFound) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with telegram id $id not found")
        }
    }

}