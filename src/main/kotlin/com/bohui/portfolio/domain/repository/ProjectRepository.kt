package com.bohui.portfolio.domain.repository

import com.bohui.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProjectRepository : JpaRepository<Project, Long> {

    fun findAllByIsActive(isActive: Boolean): List<Project>

    // 상위에 정의된 기능이여서 override를 해줘야한다.
    override fun findById(id: Long): Optional<Project>
}