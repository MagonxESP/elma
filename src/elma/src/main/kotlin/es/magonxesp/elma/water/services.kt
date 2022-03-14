package es.magonxesp.elma.water

import es.magonxesp.elma.water.application.create.WaterCreator
import es.magonxesp.elma.water.infraestructure.persistence.MysqlWaterRepository

fun waterCreator(): WaterCreator = WaterCreator(MysqlWaterRepository())
