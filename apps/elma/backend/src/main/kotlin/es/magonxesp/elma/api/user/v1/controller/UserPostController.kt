package es.magonxesp.elma.api.user.v1.controller

import es.magonxesp.elma.api.user.v1.CreateRequest
import es.magonxesp.elma.user.creator
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/user")
class UserPostController {

    @PostMapping("/create")
    fun create(@RequestBody request: CreateRequest): Map<String, String> {
        creator().createIfNotExistByTelegramId(UUID.fromString(request.id), request.telegramUserId)

        return mapOf("status" to "created")
    }

}