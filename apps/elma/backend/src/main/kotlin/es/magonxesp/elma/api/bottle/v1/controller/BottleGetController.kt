package es.magonxesp.elma.api.bottle.v1.controller

import es.magonxesp.elma.api.bottle.v1.BottleResponse
import es.magonxesp.elma.bottle.bottleFinder
import es.magonxesp.elma.bottle.currentBottle
import es.magonxesp.elma.bottle.domain.Bottle
import es.magonxesp.elma.bottle.domain.BottleException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/bottle")
class BottleGetController {

    private fun aggregateToResponse(bottle: Bottle): BottleResponse = BottleResponse(
        id = bottle.id.value.toString(),
        owner = bottle.owner.value.toString(),
        capacity = bottle.capacity.value,
        startDate = bottle.started.value,
        finished = bottle.finished.value,
        current = bottle.current.value,
    )

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            bottleFinder().find(UUID.fromString(id)).let {
                ResponseEntity.ok(aggregateToResponse(it))
            }
        } catch (exception: BottleException.NotFound) {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to exception.message))
        }
    }

    @GetMapping("/current")
    fun current(@RequestBody request: Map<String, String>): ResponseEntity<Any> {
        if (!request.containsKey("user")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(mapOf("error" to "The user field with the user id as value is required"))
        }

        return try {
            currentBottle().getCurrent(UUID.fromString(request["user"])).let {
                ResponseEntity.ok(aggregateToResponse(it))
            }
        } catch (exeption: BottleException.NotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to exeption.message))
        }
    }

}