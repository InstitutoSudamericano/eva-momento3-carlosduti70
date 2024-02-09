package com.example.evam3.service

import com.example.evam3.entity.Film
import com.example.evam3.entity.Scene
import com.example.evam3.repository.FilmRepository
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

    @Mock
    lateinit var filmRepository: FilmRepository

    val jsonStringScene = File("./src/test/resources/scene.json").readText(Charsets.UTF_8)
    val sceneMock = Gson().fromJson(jsonStringScene, Scene::class.java)
    val jsonStringFilm = File("./src/test/resources/film.json").readText(Charsets.UTF_8)
    val filmMock = Gson().fromJson(jsonStringFilm, Film::class.java)

    @Test
    fun saveSceneeWhenIsCorrect() {
        Mockito.`when`(filmRepository.findById(sceneMock.filmId)).thenReturn(filmMock)
        Mockito.`when`(sceneRepository.save(Mockito.any(Scene::class.java))).thenReturn(sceneMock)
        val actualResponse = sceneService.save(sceneMock)
        Assertions.assertEquals(actualResponse.id, sceneMock.id)
        //Lee el archivo desde l ruta
        val jsonString = File("./src/test/resources/scene.json").readText(Charsets.UTF_8)
        // convierte en objeto de tipo Invoice
        val invoiceMock = Gson().fromJson(jsonString, Scene::class.java)

    }
}