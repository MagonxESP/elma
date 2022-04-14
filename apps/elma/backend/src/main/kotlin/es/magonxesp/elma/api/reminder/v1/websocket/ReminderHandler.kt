package es.magonxesp.elma.api.reminder.v1.websocket

import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler


class ReminderHandler : TextWebSocketHandler() {

    override fun afterConnectionEstablished(session: WebSocketSession) {
        ReminderSessions.instance.add(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        ReminderSessions.instance.remove(session)
    }

}