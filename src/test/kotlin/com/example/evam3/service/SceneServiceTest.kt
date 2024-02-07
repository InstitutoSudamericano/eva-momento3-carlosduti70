package com.example.evam3.service

import com.example.evam3.entity.Scene
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
class SceneServiceTest {

    @InjectMocks
    lateinit var sceneService: SceneService // Clase que voy a probar

    @Mock // Objeto simulado
    lateinit var sceneRepository: SceneRepository

    val jsonStringScene = File("./src/test/resources/scene.json").readText(Charsets.UTF_8)
    val sceneMock = Gson().fromJson(jsonStringScene, Scene::class.java)

    @Test
    fun save() {
        Mockito.`when`(sceneRepository.save(Mockito.any(Scene::class.java))).thenReturn(sceneMock)
        val response = sceneService.save(sceneMock)
        Assertions.assertEquals(response.id, sceneMock.id)
    }
}