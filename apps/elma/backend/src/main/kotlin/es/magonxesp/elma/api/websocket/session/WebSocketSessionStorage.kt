package es.magonxesp.elma.api.websocket.session

import org.springframework.web.socket.WebSocketSession

interface WebSocketSessionStorage {
    fun add(session: WebSocketSession)
    fun remove(session: WebSocketSession)
}
