package com.bohui.portfolio.admin.context.project.service

import com.bohui.portfolio.admin.data.TableDTO
import com.bohui.portfolio.admin.exception.AdminBadRequestException
import com.bohui.portfolio.domain.entity.Experience
import com.bohui.portfolio.domain.entity.ExperienceDetail
import com.bohui.portfolio.domain.entity.Project
import com.bohui.portfolio.domain.entity.ProjectDetail
import com.bohui.portfolio.domain.repository.ExperienceRepository
import com.bohui.portfolio.domain.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class AdminProjectService(
    private val projectRepository: ProjectRepository
) {

    fun getProjectTable(): TableDTO {
        val classInfo = Project::class
        val entities = projectRepository.findAll()

        return TableDTO.from(classInfo, entities, "details", "skills") // 제외를 안하면 한셀에 디테일 정보가 다 들어간다.
    }

    fun getProjectDetailTable(id: Long?): TableDTO {
        val classInfo = ProjectDetail::class
        val entities = if (id != null) projectRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수 없습니다.") }
            .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }
}
