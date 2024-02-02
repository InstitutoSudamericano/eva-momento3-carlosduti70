package com.example.evam3.repository

import com.example.evam3.entity.Character
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository: JpaRepository<Character, Long> {

}