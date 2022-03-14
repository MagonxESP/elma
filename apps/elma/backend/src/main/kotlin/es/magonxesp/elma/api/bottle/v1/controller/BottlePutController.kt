package es.magonxesp.elma.api.bottle.v1.controller

import es.magonxesp.elma.bottle.bottleFinder
import es.magonxesp.elma.bottle.currentBottle
import es.magonxesp.elma.bottle.domain.BottleException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/bottle")
class BottlePutController {

    @PutMapping("/current")
    fun setCurrent(@RequestBody request: Map<String, String>): ResponseEntity<Any> {
        if (!request.containsKey("bottle")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(mapOf("error" to "The bottle field with the bottle id as value is required"))
        }

        return try {
            val bottle = bottleFinder().find(UUID.fromString(request["bottle"]))
            currentBottle().setCurrent(bottle)

            ResponseEntity.ok(mapOf("status" to "updated"))
        } catch (exception: BottleException.NotFound) {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to exception.message))
        }
    }

}