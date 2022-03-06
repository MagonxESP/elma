package es.magonxesp.elma.glass.application.create

import es.magonxesp.elma.glass.domain.Glass
import es.magonxesp.elma.glass.domain.GlassRepository
import java.util.UUID

class GlassCreator(private val repository: GlassRepository) {

    fun create(id: UUID, user: UUID): Glass {
        val glass = Glass.fromPrimitives(id, user)

        repository.save(glass)

        return glass
    }

}