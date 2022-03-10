package es.magonxesp.elma.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/")
    fun index(): Map<String, String> {
        return mapOf(Pair("Hola", "mundo"))
    }

}