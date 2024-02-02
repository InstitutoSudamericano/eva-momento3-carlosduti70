package com.example.evam3.service

import com.example.evam3.entity.Scene
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {
    @Autowired
    lateinit var sceneRepository: SceneRepository

    fun list ():List<Scene>{
        return sceneRepository.findAll()
    }

    fun save (scene: Scene): Scene{
        try{
            scene.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Film no debe ser vacio")
            return sceneRepository.save(scene)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }

    }
}