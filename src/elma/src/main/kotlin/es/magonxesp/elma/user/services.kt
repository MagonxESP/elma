package es.magonxesp.elma.user

import es.magonxesp.elma.user.application.create.UserCreator
import es.magonxesp.elma.user.application.find.UserFinder
import es.magonxesp.elma.user.infraestructure.persistence.MysqlUserRepository

fun creator(): UserCreator = UserCreator(MysqlUserRepository())
fun finder(): UserFinder = UserFinder(MysqlUserRepository())
