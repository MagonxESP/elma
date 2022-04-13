package es.magonxesp.elma.api.http.bottle.v1.controller

import es.magonxesp.elma.api.http.bottle.v1.BottleResponse
import es.magonxesp.elma.api.http.bottle.v1.CreateRequest
import es.magonxesp.elma.bottle.bottleCreator
import es.magonxesp.elma.shared.domain.date.InvalidDateTimeFormatException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/bottle")
class BottlePostController {

    @PostMapping("/create")
    fun create(@RequestBody request: CreateRequest): ResponseEntity<Any> {
        try {
            val created = bottleCreator().create(
                id = UUID.fromString(request.id),
                owner = UUID.fromString(request.owner),
                capacity = request.capacity,
                startDate = request.startDate,
                current = request.current
            ).let {
                BottleResponse(
                    id = it.id.value.toString(),
                    owner = it.owner.value.toString(),
                    capacity = it.capacity.value,
                    startDate = it.started.value,
                    finished = it.finished.value,
                    current = it.current.value
                )
            }

            return ResponseEntity(created, HttpStatus.CREATED)
        } catch (exception: InvalidDateTimeFormatException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to exception.message))
        }
    }

}