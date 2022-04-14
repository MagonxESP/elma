package es.magonxesp.elma.api.reminder.v1.websocket

import es.magonxesp.elma.api.shared.websocket.session.InMemoryWebSocketSessionStorage

class ReminderSessions : InMemoryWebSocketSessionStorage() {
    companion object {
        val instance = ReminderSessions()
    }
}
