package es.magonxesp.elma.api.shared.websocket

import es.magonxesp.elma.api.shared.websocket.session.WebSocketSessionStorage
import org.springframework.web.socket.TextMessage

class WebSocketUtils(private val sessions: WebSocketSessionStorage) {

    fun broadcast(message: TextMessage) {
        sessions.all().forEach {
            it.sendMessage(message)
        }
    }

}