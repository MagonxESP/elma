package es.magonxesp.elma.glass.domain

import es.magonxesp.elma.shared.domain.glass.GlassId

interface GlassRepository {
    fun find(id: GlassId): Glass
    fun save(glass: Glass)
}