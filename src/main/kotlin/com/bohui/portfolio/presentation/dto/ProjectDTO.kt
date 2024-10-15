package com.bohui.portfolio.presentation.dto

import com.bohui.portfolio.domain.entity.Project

class ProjectDTO(
    val name: String,
    val description: String,
    val startYearMonth: String,
    val endYearMonth: String?,
    val details: List<ProjectDetailDTO>,
    val skills: List<SkillDTO>?
) {
    constructor(project: Project) : this(
        name = project.name,
        description = project.description,
        startYearMonth = "${project.startYear}.${project.startMonth}", // 포맷팅 사용 2024.10
        endYearMonth = project.getEndYearMonth(),
        details = project.details.filter { it.isActive }.map { ProjectDetailDTO(it) },
        skills = project.skills.map { it.skill }.filter { it.isActive }
            .map { SkillDTO(it) } // SkillDTO의 리스트가 skills로 들어간다.
    )
}
