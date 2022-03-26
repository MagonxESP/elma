package es.magonxesp.elma.api.reminder.v1.controller

import es.magonxesp.elma.api.reminder.v1.ReminderRequest
import es.magonxesp.elma.api.reminder.v1.ReminderResponse
import es.magonxesp.elma.reminder.creator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/reminder")
class ReminderPostController {

    @PostMapping("/create")
    fun create(@RequestBody request: ReminderRequest): ResponseEntity<ReminderResponse> {
        val created = creator().create(
            id = UUID.fromString(request.id),
            toUser = UUID.fromString(request.toUser),
            scheduledDate = request.scheduledDate,
            sendedDate = request.sendedDate
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(ReminderResponse(
            created.id.value.toString(),
            created.toUser.value.toString(),
            created.scheduled.value,
            created.sended.value
        ))
    }

}