package es.magonxesp.elma.api.reminder.v1.task

import es.magonxesp.elma.api.reminder.v1.ReminderResponse
import es.magonxesp.elma.api.reminder.v1.websocket.ReminderSessions
import es.magonxesp.elma.api.shared.websocket.WebSocketUtils
import es.magonxesp.elma.reminder.finder as reminderFinder
import es.magonxesp.elma.shared.domain.date.DateUtils
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import java.time.LocalDateTime
import java.util.logging.Logger

@Component
class ReminderSenderTask {

    @Scheduled(cron = "*/3 * * * * *")
    fun task() {
        val reminders = reminderFinder().betweenScheduledDates(
            from = DateUtils.format(LocalDateTime.now().minusHours(1)),
            to = DateUtils.now()
        )

        for (reminder in reminders) {
            try {
                WebSocketUtils(ReminderSessions.instance).broadcast(TextMessage(Json.encodeToString(ReminderResponse.fromAggregate(reminder))))
                Logger.getGlobal().info("Reminder send signal for user ${reminder.toUser.value}")
            } catch (exception: Exception) {
                Logger.getGlobal().warning("${exception::class.java} ocurrend during reminder send with message: ${exception.message}")
            }
        }
    }

}
