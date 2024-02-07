package com.example.evam3.service

import com.example.evam3.entity.Film
import com.example.evam3.entity.Scene
import com.example.evam3.repository.FilmRepository
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
internal class FilmServiceTest {

    @InjectMocks
    lateinit var filmService: FilmService

    @Mock // Objeto simulado
    lateinit var filmRepository: FilmRepository

    val jsonStringFilm = File("./src/test/resources/film.json").readText(Charsets.UTF_8)
    val filmMock = Gson().fromJson(jsonStringFilm, Film::class.java)


    @Test
    fun list() {

    }

    @Test
    fun save() {

        Mockito.`when`(filmRepository.save(Mockito.any(Film::class.java))).thenReturn(filmMock)
        val response = filmService.save(filmMock)
        Assertions.assertEquals(response.id, filmMock.id)
    }
}