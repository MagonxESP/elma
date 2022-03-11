package es.magonxesp.elma.bottle

import es.magonxesp.elma.bottle.application.create.BottleCreator
import es.magonxesp.elma.bottle.application.current.CurrentBottle
import es.magonxesp.elma.bottle.infraestructure.persistence.MysqlBottleRepository

fun creator(): BottleCreator = BottleCreator(MysqlBottleRepository())
fun current(): CurrentBottle = CurrentBottle(MysqlBottleRepository())
