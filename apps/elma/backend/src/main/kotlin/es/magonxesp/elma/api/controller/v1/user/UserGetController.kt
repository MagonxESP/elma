package es.magonxesp.elma.api.controller.v1.user

import es.magonxesp.elma.api.requests.v1.user.UserResponse
import es.magonxesp.elma.user.domain.UserException
import es.magonxesp.elma.user.finder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/user")
class UserGetController {

    @GetMapping("/telegram-id/{id}")
    @ResponseBody
    fun findByTelegramId(@PathVariable id: Int): ResponseEntity<Any> {
        return try {
            finder().findByTelegramUserId(id).let {
                ResponseEntity.ok(UserResponse(it.id.value.toString(), it.telegramUserId.value))
            }
        } catch (exception: UserException.NotFound) {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to  "User with telegram id $id not found"))
        }
    }

}