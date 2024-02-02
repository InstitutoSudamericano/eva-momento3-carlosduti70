package com.example.evam3.repository

import com.example.evam3.entity.Scene
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SceneRepository: JpaRepository<Scene, Long> {

}