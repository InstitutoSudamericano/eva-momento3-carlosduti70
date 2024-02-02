package com.example.evam3.service

import com.example.evam3.entity.Character
import com.example.evam3.repository.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CharacterService {
    @Autowired
    lateinit var characterRepository: CharacterRepository

    fun list ():List<Character>{
        return characterRepository.findAll()
    }

    fun save (character:Character): Character{
        try{
            character.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Character no debe ser vacio")
            return characterRepository.save(character)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }

    }
}