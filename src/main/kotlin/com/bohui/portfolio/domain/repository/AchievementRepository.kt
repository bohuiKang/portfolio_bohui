package com.bohui.portfolio.domain.repository

import com.bohui.portfolio.domain.entity.Achievement
import org.springframework.data.jpa.repository.JpaRepository

interface AchievementRepository : JpaRepository<Achievement, Long> {

    // select * from achievement where is_active = :isActive
    fun findAllByIsActive(isActive: Boolean): List<Achievement> //리스트로 조회해온다.

}