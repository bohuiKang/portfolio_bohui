package com.bohui.portfolio.domain.repository

import com.bohui.portfolio.domain.entity.Experience
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ExperienceRepository : JpaRepository<Experience, Long> {

    @Query("select e from Experience e left join fetch e.details where e.isActive = :isActive")
    fun findAllByIsActive(isActive: Boolean): List<Experience>

    @Query("select e from Experience e left join fetch e.details where e.id = :id")
    // 상위에 정의된 기능이여서 override를 해줘야한다.
    override fun findById(id: Long): Optional<Experience>

}