package es.magonxesp.elma.api.shared.websocket.config

import es.magonxesp.elma.api.reminder.v1.websocket.ReminderHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.*

@Configuration
@EnableWebSocket
class WebSocketConfiguration : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(ReminderHandler(), "/ws/v1/reminder/pending-to-send")
    }

}
