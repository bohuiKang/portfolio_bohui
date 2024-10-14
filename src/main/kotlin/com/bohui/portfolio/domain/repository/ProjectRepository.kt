package com.bohui.portfolio.domain.repository

import com.bohui.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ProjectRepository : JpaRepository<Project, Long> {

    @Query("select p from Project p left join fetch p.skills s left join fetch s.skill where p.isActive = :isActive")
    fun findAllByIsActive(isActive: Boolean): List<Project>

    @Query("select p from Project p join fetch p.details where p.id = :id")
    // 상위에 정의된 기능이여서 override를 해줘야한다.
    override fun findById(id: Long): Optional<Project>
}