package es.magonxesp.elma.user.infraestructure.persistence.hibernate

import es.magonxesp.elma.user.domain.User
import jdk.jfr.Unsigned
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
open class UserEntity {
    @Id
    @Column(name = "id", nullable = true, length = 36)
    open var id: String? = null

    @Unsigned
    @Column(name = "telegram_user_id", nullable = true)
    open var telegramUserId: Int? = null

    companion object {
        fun fromAggregate(user: User): UserEntity = UserEntity().apply {
            id = user.id.value.toString()
            telegramUserId = user.telegramUserId.value
        }
    }

    fun toAggregate(): User = User.fromPrimitives(
        id = UUID.fromString(id!!),
        telegramUserId = telegramUserId!!
    )
}