package com.example.evam3.controller

import com.example.evam3.entity.Film
import com.example.evam3.entity.Scene
import com.example.evam3.service.SceneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/scene")
class SceneController {
    @Autowired
    lateinit var sceneService: SceneService

    @GetMapping
    fun list (): ResponseEntity<*> {
        return ResponseEntity(sceneService.list(), HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody scene: Scene): ResponseEntity<*> {
        return ResponseEntity<Scene>(sceneService.save(scene), HttpStatus.CREATED)
    }

    @PutMapping
    fun update (@RequestBody scene: Scene):ResponseEntity<Scene>{
        return ResponseEntity(sceneService.update(scene), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return sceneService.delete(id)
    }
}