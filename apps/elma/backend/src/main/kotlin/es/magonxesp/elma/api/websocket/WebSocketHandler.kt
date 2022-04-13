package es.magonxesp.elma.api.websocket

import es.magonxesp.elma.api.websocket.session.InMemoryWebSocketSessionStorage
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class WebSocketHandler : TextWebSocketHandler() {

    override fun afterConnectionEstablished(session: WebSocketSession) {
        InMemoryWebSocketSessionStorage.instance.add(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        InMemoryWebSocketSessionStorage.instance.remove(session)
    }

}