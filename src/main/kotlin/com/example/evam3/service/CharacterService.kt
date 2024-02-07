package com.example.evam3.service

import com.example.evam3.entity.Character
import com.example.evam3.repository.CharacterRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CharacterService {
    @Autowired
    lateinit var sceneRepository: SceneRepository
    @Autowired
    lateinit var characterRepository: CharacterRepository



    fun list ():List<Character>{
        return characterRepository.findAll()
    }

    fun save (character:Character): Character{
        try{
            sceneRepository.findById(character.sceneId)
                ?: throw Exception("Id de la escena no encontrado")

            character.name?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Descripcion no debe ser vacio")
            
            character.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Descripcion no debe ser vacio")

            // Calcular el costo total actual de los personajes en la escena
            val scene = sceneRepository.findById(character.sceneId)
            val currentTotalCost = characterRepository.sumCostBySceneId(character.sceneId!!) ?: 0.0
            // Verificar si el nuevo personaje excede el presupuesto de la escena
            if (scene != null) {
                if (currentTotalCost + (character.cost ?: 0.0) > (scene.budget ?: 0.0)) {
                    throw Exception("El costo total de los personajes excede el presupuesto de la escena")
                }
            }

            character.side?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Bando no debe ser vacio")
            return characterRepository.save(character)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }

    }

    fun update(character: Character): Character {
        try {
            characterRepository.findById(character.id)
                ?: throw Exception("ID no existe")

            return characterRepository.save(character)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = characterRepository.findById(id)
                ?: throw Exception("ID no existe")
            characterRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}