package com.example.evam3.repository

import com.example.evam3.entity.Scene
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SceneRepository: JpaRepository<Scene, Long> {
    fun findById (id: Long?): Scene?

    @Query("SELECT COALESCE(SUM(s.minutes), 0) FROM Scene s WHERE s.filmId = :filmId")
    fun sumMinutesByFilmId(@Param("filmId") filmId: Long): Long?

}