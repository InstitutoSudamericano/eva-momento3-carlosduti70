package com.example.evam3.controller

import com.example.evam3.entity.Character
import com.example.evam3.service.CharacterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/character")
class CharacterController {
    @Autowired
    lateinit var characterService: CharacterService

    @GetMapping
    fun list (): ResponseEntity<*> {
        return ResponseEntity(characterService.list(), HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody character: Character): ResponseEntity<*> {
        return ResponseEntity<Character>(characterService.save(character), HttpStatus.CREATED)
    }

}