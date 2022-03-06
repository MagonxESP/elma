package es.magonxesp.elma.user.infraestructure.persistence

import es.magonxesp.elma.shared.domain.user.UserId
import es.magonxesp.elma.shared.infraestructure.hibernate.HibernateDatabaseSession
import es.magonxesp.elma.user.domain.User
import es.magonxesp.elma.user.domain.UserException
import es.magonxesp.elma.user.domain.UserRepository
import es.magonxesp.elma.user.infraestructure.persistence.hibernate.UserEntity

class MysqlUserRepository : UserRepository {

    override fun all(): Array<User> {
        val result = HibernateDatabaseSession().query {
            session -> session.createQuery("SELECT u FROM UserEntity u", UserEntity::class.java)
        }

        if (result.isNullOrEmpty()) {
            return arrayOf()
        }

        return result.map {
            (it as UserEntity).toAggregate()
        }.toTypedArray()
    }

    override fun find(id: UserId): User {
        val result = HibernateDatabaseSession().query {
            session ->
                session.createQuery(
                    "SELECT u FROM UserEntity u WHERE id = :id",
                    UserEntity::class.java
                ).setParameter("id", id.value.toString())
        }

        if (result.isNullOrEmpty()) {
            throw UserException.NotFound("User with id ${id.value} not found")
        }

        return (result.first() as UserEntity).toAggregate()
    }

    override fun findByTelegramId(telegramId: User.UserTelegramId): User {
        val result = HibernateDatabaseSession().query {
            session ->
                session.createQuery(
                    "SELECT u FROM UserEntity u WHERE telegramUserId = :id",
                    UserEntity::class.java
                ).setParameter("id", telegramId.value)
        }

        if (result.isNullOrEmpty()) {
            throw UserException.NotFound("User with telegram user id ${telegramId.value} not found")
        }

        return (result.first() as UserEntity).toAggregate()
    }

    override fun save(user: User) {
        HibernateDatabaseSession().transaction {
            save(UserEntity.fromAggregate(user))
        }
    }

}