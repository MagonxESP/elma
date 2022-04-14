package es.magonxesp.elma.api.shared.websocket.session

import org.springframework.web.socket.WebSocketSession

open class InMemoryWebSocketSessionStorage : WebSocketSessionStorage {
    companion object {
        val instance = InMemoryWebSocketSessionStorage()
    }

    protected val sessions: MutableMap<String, WebSocketSession> = mutableMapOf()

    override fun add(session: WebSocketSession) {
        sessions[session.id] = session
    }

    override fun remove(session: WebSocketSession) {
        sessions.remove(session.id)
    }

    override fun all(): Array<WebSocketSession> {
        return sessions.toList().map {
            it.second
        }.toTypedArray()
    }
}