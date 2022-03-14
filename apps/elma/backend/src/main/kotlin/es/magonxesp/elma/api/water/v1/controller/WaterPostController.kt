package es.magonxesp.elma.api.water.v1.controller

import es.magonxesp.elma.api.water.v1.WaterRequest
import es.magonxesp.elma.api.water.v1.WaterResponse
import es.magonxesp.elma.water.waterCreator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/water")
class WaterPostController {

    @PostMapping("/create")
    fun create(@RequestBody request: WaterRequest): ResponseEntity<WaterResponse> {
        return waterCreator().create(
            id = UUID.fromString(request.id),
            step = request.step,
            bottle = UUID.fromString(request.bottleId)
        ).let {
            ResponseEntity.status(HttpStatus.CREATED).body(WaterResponse(
                id = it.id.value.toString(),
                step = it.step.value,
                bottleId = it.bottle.value.toString()
            ))
        }
    }

}