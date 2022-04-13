package es.magonxesp.elma.api.websocket

import es.magonxesp.elma.api.websocket.reminder.v1.ReminderHandler
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.*

@Configuration
@EnableWebSocket
class WebSocketConfiguration : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(WebSocketHandler(), "/ws")
        registry.addHandler(ReminderHandler(), "/ws/v1/reminder/pending-to-send")
    }

}
