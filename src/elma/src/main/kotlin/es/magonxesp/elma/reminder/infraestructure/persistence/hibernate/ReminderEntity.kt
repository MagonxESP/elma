package es.magonxesp.elma.reminder.infraestructure.persistence.hibernate

import es.magonxesp.elma.reminder.domain.Reminder
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "reminder")
open class ReminderEntity {

    @Id
    @Column(name = "id", nullable = true)
    open var id: String? = null

    @Column(name = "to_user", nullable = true)
    open var toUser: String? = null

    @Column(name = "scheduled", nullable = true)
    open var scheduled: String? = null

    @Column(name = "sended", nullable = true)
    open var sended: String? = null

    companion object {
        fun fromAggregate(reminder: Reminder): ReminderEntity = ReminderEntity().apply {
            this.id = reminder.id.value.toString()
            this.toUser = reminder.toUser.value.toString()
            this.scheduled = reminder.scheduled.value
            this.sended = reminder.sended.value
        }
    }

    fun toAggregate(): Reminder = Reminder.fromPrimitives(
        id = UUID.fromString(id!!),
        toUser = UUID.fromString(toUser!!),
        scheduled = scheduled!!,
        sended = sended
    )
}
