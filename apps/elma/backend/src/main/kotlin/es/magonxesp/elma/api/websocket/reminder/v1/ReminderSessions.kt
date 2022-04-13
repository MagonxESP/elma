package es.magonxesp.elma.api.websocket.reminder.v1

import es.magonxesp.elma.api.websocket.session.InMemoryWebSocketSessionStorage

class ReminderSessions : InMemoryWebSocketSessionStorage() {
    companion object {
        val instance = ReminderSessions()
    }
}
