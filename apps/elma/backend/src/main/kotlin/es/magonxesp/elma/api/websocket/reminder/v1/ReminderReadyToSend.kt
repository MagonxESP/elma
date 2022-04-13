package es.magonxesp.elma.api.websocket.reminder.v1

import es.magonxesp.elma.reminder.domain.Reminder
import org.springframework.messaging.handler.annotation.MessageMapping

class ReminderReadyToSend {

    @MessageMapping("/v1/reminder/send")
    fun send(reminder: Reminder) {}

}