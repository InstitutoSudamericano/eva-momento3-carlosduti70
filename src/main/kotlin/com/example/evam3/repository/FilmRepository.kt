package com.example.evam3.repository

import com.example.evam3.entity.Film
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FilmRepository:JpaRepository<Film, Long> {
    fun findById (id: Long?): Film?
}