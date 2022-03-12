package es.magonxesp.elma.bottle

import es.magonxesp.elma.bottle.application.create.BottleCreator
import es.magonxesp.elma.bottle.application.current.CurrentBottle
import es.magonxesp.elma.bottle.application.finder.BottleFinder
import es.magonxesp.elma.bottle.infraestructure.persistence.MysqlBottleRepository

val bottleRepository = MysqlBottleRepository()

fun bottleCreator(): BottleCreator = BottleCreator(bottleRepository)
fun currentBottle(): CurrentBottle = CurrentBottle(bottleRepository)
fun bottleFinder(): BottleFinder = BottleFinder(bottleRepository)
