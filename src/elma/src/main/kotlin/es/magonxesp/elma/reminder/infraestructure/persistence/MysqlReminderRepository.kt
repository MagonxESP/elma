package es.magonxesp.elma.reminder.infraestructure.persistence

import es.magonxesp.elma.reminder.domain.Reminder
import es.magonxesp.elma.reminder.domain.ReminderException
import es.magonxesp.elma.reminder.domain.ReminderRepository
import es.magonxesp.elma.reminder.infraestructure.persistence.hibernate.ReminderEntity
import es.magonxesp.elma.shared.domain.user.UserId
import es.magonxesp.elma.shared.infraestructure.hibernate.HibernateDatabaseSession
import org.hibernate.Session

class MysqlReminderRepository : ReminderRepository {

    override fun find(id: Reminder.ReminderId): Reminder {
        val result = HibernateDatabaseSession().query {
            session: Session ->
                session.createQuery("SELECT r FROM ReminderEntity r WHERE id = :id")
                    .setParameter("id", id.value.toString())
        }

        if (result.isNullOrEmpty()) {
            throw ReminderException.NotFound("Reminder with id ${id.value} not found")
        }

        return (result.first() as ReminderEntity).toAggregate()
    }

    override fun findByUser(userId: UserId): Array<Reminder> {
        val result = HibernateDatabaseSession().query {
            session: Session ->
                session.createQuery("SELECT r FROM ReminderEntity r WHERE toUser = :id")
                    .setParameter("id", userId.value.toString())
        }

        if (result.isNullOrEmpty()) {
            return arrayOf()
        }

        return result.map {
            (it as ReminderEntity).toAggregate()
        }.toTypedArray()
    }

    override fun save(reminder: Reminder) {
        HibernateDatabaseSession().transaction {
            save(ReminderEntity.fromAggregate(reminder))
        }
    }

}