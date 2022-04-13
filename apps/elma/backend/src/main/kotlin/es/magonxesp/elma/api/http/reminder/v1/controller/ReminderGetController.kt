package es.magonxesp.elma.api.http.reminder.v1.controller

import es.magonxesp.elma.api.http.reminder.v1.ReminderResponse
import es.magonxesp.elma.reminder.finder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/reminder")
class ReminderGetController {

    @GetMapping("/between/{from}/{to}")
    fun findScheduledTo(@PathVariable from: String, @PathVariable to: String): Array<ReminderResponse> {
        return finder().betweenScheduledDates(from, to).map {
            ReminderResponse(
                it.id.value.toString(),
                it.toUser.value.toString(),
                it.scheduled.value,
                it.sended.value
            )
        }.toTypedArray()
    }

}
