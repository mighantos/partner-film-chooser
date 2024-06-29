package com.mighantos.partner_film_chooser.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/films")
class FilmsController {

    @GetMapping
    fun getAllFilms(): String {
        return "Hello world"
    }
}