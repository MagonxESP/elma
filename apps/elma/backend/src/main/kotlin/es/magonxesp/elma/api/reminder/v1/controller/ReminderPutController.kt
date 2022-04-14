package es.magonxesp.elma.api.reminder.v1.controller

import es.magonxesp.elma.api.reminder.v1.ReminderResponse
import es.magonxesp.elma.api.reminder.v1.ReminderUpdateRequest
import es.magonxesp.elma.reminder.domain.Reminder
import es.magonxesp.elma.reminder.finder
import es.magonxesp.elma.reminder.updater
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/reminder")
class ReminderPutController {

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody request: ReminderUpdateRequest): ResponseEntity<Any> {
        try {
            val reminder = finder().find(UUID.fromString(id))

            if (!request.sendedDate.isNullOrEmpty()) {
                reminder.sended = Reminder.ReminderSendedDate(request.sendedDate)
            }

            updater().update(reminder)

            return ResponseEntity.ok(ReminderResponse.fromAggregate(reminder))
        } catch (exception: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to exception.message))
        }
    }

}