package es.magonxesp.elma.glass

import es.magonxesp.elma.glass.application.create.GlassCreator
import es.magonxesp.elma.glass.infraestructure.persistence.MysqlGlassRepository

fun glassCreator(): GlassCreator = GlassCreator(MysqlGlassRepository())
