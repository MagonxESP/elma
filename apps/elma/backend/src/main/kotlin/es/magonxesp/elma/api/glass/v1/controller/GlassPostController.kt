package es.magonxesp.elma.api.glass.v1.controller

import es.magonxesp.elma.api.glass.v1.GlassRequest
import es.magonxesp.elma.api.glass.v1.GlassResponse
import es.magonxesp.elma.glass.glassCreator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/glass")
class GlassPostController {

    @PostMapping("/create")
    fun create(@RequestBody request: GlassRequest): ResponseEntity<GlassResponse> {
        return glassCreator().create(
            id = UUID.fromString(request.id),
            user = UUID.fromString(request.userId)
        ).let {
            ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GlassResponse(
                    id = it.id.value.toString(),
                    userId = it.user.value.toString()
                ))
        }
    }

}