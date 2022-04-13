package es.magonxesp.elma.api.task

import es.magonxesp.elma.api.websocket.reminder.v1.ReminderSessions
import es.magonxesp.elma.reminder.finder as reminderFinder
import es.magonxesp.elma.shared.domain.date.DateUtils
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketMessage
import es.magonxesp.elma.user.finder as userFinder
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
                val user = userFinder().find(reminder.toUser.value)

                ReminderSessions.instance.sessions.forEach { (key, session) ->
                    session.sendMessage(
                        TextMessage(
                            Json.encodeToString(
                                mapOf(
                                    "user" to user,
                                    "reminder" to reminder
                                )
                            )
                        )
                    )
                }

                Logger.getGlobal().info("Reminder send signal for user ${user.id.value}")
            } catch (exception: Exception) {
                Logger.getGlobal().warning("${exception::class.java} ocurrend during reminder send with message: ${exception.message}")
            }
        }
    }

}
