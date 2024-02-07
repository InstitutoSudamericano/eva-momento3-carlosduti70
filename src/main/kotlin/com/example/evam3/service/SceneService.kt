package com.example.evam3.service

import com.example.evam3.entity.Scene
import com.example.evam3.repository.CharacterRepository
import com.example.evam3.repository.FilmRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {


    @Autowired
    lateinit var sceneRepository: SceneRepository

    @Autowired
    lateinit var filmRepository: FilmRepository

    fun list ():List<Scene>{
        return sceneRepository.findAll()
    }

    fun save (scene: Scene): Scene{
        try{
            filmRepository.findById(scene.filmId)
                ?: throw Exception("Id de pelicula no encontrado")
            scene.title?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Titulo no debe ser vacio")
            scene.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Descripcion no debe ser vacio")
            scene.budget?.takeIf { it > 0.00 }
                ?: throw Exception("presupuesto no debe ser 0")
            scene.minutes?.takeIf { it> 0.00 }
                ?: throw Exception("Mimutos no debe ser 0")

            val film = filmRepository.findById(scene.filmId)
            val currentTotalCost = sceneRepository.sumMinutesByFilmId(scene.filmId!!) ?: 0

            if (film != null) {
                if (currentTotalCost + (scene.minutes ?: 0) > (film.duration?: 0)) {
                    throw Exception("El total de minutos excede la duracion de la pelicula")
                }
            }
            return sceneRepository.save(scene)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }

    }

    fun update(scene: Scene): Scene {
        try {
            sceneRepository.findById(scene.id)
                ?: throw Exception("ID no existe")

            return sceneRepository.save(scene)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = sceneRepository.findById(id)
                ?: throw Exception("ID no existe")
            sceneRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}