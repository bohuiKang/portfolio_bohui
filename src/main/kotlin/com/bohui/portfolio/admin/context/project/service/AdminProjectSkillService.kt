package com.bohui.portfolio.admin.context.project.service

import com.bohui.portfolio.admin.data.TableDTO
import com.bohui.portfolio.domain.repository.ProjectRepository
import com.bohui.portfolio.domain.repository.ProjectSkillRepository
import com.bohui.portfolio.domain.repository.SkillRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminProjectSkillService(
    private val projectRepository: ProjectRepository,
    private val skillRepository: SkillRepository,
    private val projectSkillRepository: ProjectSkillRepository
) {

    @Transactional
    fun getProjectSkillTable(): TableDTO {

        val projects = projectRepository.findAll()
        val columns = mutableListOf<String>(
            "id", "projectId", "projectName", "skillId",
            "skillName", "createdDateTime", "updatedDateTime"
        )
        val records = mutableListOf<MutableList<String>>()
        for (project in projects) {
            project.skills.forEach {
                val record = mutableListOf<String>()
                record.add(it.id.toString()) // 프로젝트스킬 id
                record.add(it.project.id.toString()) // 프로젝트 id
                record.add(it.project.name) // 프로젝트 name
                record.add(it.skill.id.toString()) // 스킬 id
                record.add(it.skill.name) // 스킬 name
                record.add(it.createdDateTime.toString())
                record.add(it.updatedDateTime.toString())
                records.add(record)
            }
        }

        return TableDTO(name = "ProjectSkill", columns = columns, records = records)
    }

    fun getProjectList(): List<String> {
        val projects = projectRepository.findAll()

        return projects.map { "${it.id} (${it.name})" }.toList()
    }

    fun getSkillList(): List<String> {
        val skills = skillRepository.findAll()

        return skills.map { "${it.id} (${it.name})" }.toList()
    }
}