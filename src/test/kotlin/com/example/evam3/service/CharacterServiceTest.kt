package com.example.evam3.service

import com.example.evam3.entity.Character
import com.example.evam3.entity.Scene
import com.example.evam3.repository.CharacterRepository
import com.example.evam3.repository.SceneRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
@SpringBootTest
class CharacterServiceTest {

    @InjectMocks
    lateinit var characterService: CharacterService

    @Mock
    lateinit var characterRepository: CharacterRepository

    @Mock
    lateinit var sceneRepository: SceneRepository

    val jsonStringCharacter = File("./src/test/resources/character.json").readText(Charsets.UTF_8)
    val characterMock = Gson().fromJson(jsonStringCharacter, Character::class.java)
    val jsonStringScene = File("./src/test/resources/scene.json").readText(Charsets.UTF_8)
    val sceneMock = Gson().fromJson(jsonStringScene, Scene::class.java)

    @Test
    fun saveCharacterWhenIsCorrect(){
        Mockito.`when`(sceneRepository.findById(characterMock.sceneId)).thenReturn(sceneMock)
        Mockito.`when`(characterRepository.save(Mockito.any(Character::class.java))).thenReturn(characterMock)
        val actualResponse = characterService.save(characterMock)
        Assertions.assertEquals(actualResponse.id, characterMock.id)
        //Lee el archivo desde l ruta
        val jsonString = File("./src/test/resources/character.json").readText(Charsets.UTF_8)
        // convierte en objeto de tipo Invoice
        val invoiceMock = Gson().fromJson(jsonString, Character::class.java)
    }
}