package com.example.evam3.controller

import com.example.evam3.entity.Film
import com.example.evam3.service.FilmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/film")
class FilmController {
    @Autowired
    lateinit var filmService: FilmService

    @GetMapping
    fun list (): ResponseEntity<*> {
        return ResponseEntity(filmService.list(), HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody film: Film): ResponseEntity<*> {
        return ResponseEntity<Film>(filmService.save(film), HttpStatus.CREATED)
    }

}