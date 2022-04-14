package es.magonxesp.elma.api.shared.websocket.session

import org.springframework.web.socket.WebSocketSession

interface WebSocketSessionStorage {
    fun add(session: WebSocketSession)
    fun remove(session: WebSocketSession)
    fun all(): Array<WebSocketSession>
}
