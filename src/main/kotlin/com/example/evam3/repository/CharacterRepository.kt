package com.example.evam3.repository

import com.example.evam3.entity.Character
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository: JpaRepository<Character, Long> {
    fun findById (id: Long?): Character?

    @Query("SELECT COALESCE(SUM(c.cost), 0.0) FROM Character c WHERE c.sceneId = :sceneId")
    fun sumCostBySceneId(@Param("sceneId") sceneId: Long): Double?
}