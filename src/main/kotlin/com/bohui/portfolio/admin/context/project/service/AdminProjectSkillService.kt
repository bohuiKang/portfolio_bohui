package com.bohui.portfolio.admin.context.project.service

import com.bohui.portfolio.admin.context.project.form.ProjectSkillForm
import com.bohui.portfolio.admin.data.TableDTO
import com.bohui.portfolio.admin.exception.AdminBadRequestException
import com.bohui.portfolio.admin.exception.AdminInternalServerErrorException
import com.bohui.portfolio.domain.entity.ProjectSkill
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

        return skills.map { "${it.id} (${it.name})" }.toList() // "id (name)" 형태로 list
    }

    @Transactional
    fun save(form: ProjectSkillForm) {

        // 이미 매핑된 Project - Skill 여부 검증
        val projectId = parseId(form.project)
        val skillId = parseId(form.skill)
        projectSkillRepository.findByProjectIdAndSkillId(projectId, skillId)
            .ifPresent { throw AdminBadRequestException("이미 맵핑된 데이터입니다.") }

        // 유효한 ProjectSkill 생성
        val project = projectRepository.findById(projectId)
            .orElseThrow { throw AdminBadRequestException("ID ${projectId}에 해당하는 데이터를 찾을 수 없습니다.") }
        val skill = skillRepository.findById(skillId)
            .orElseThrow { throw AdminBadRequestException("ID ${skillId}에 해당하는 데이터를 찾을 수 없습니다.") }
        val projectSkill = ProjectSkill(
            project = project,
            skill = skill
        )

        // 연관관계 세팅
        project.skills.add(projectSkill)
        /* 트랜잭션 시작할때 영속성에 들어간 프로젝트 스냅샷과 트랜잭션이 끝나고 난 뒤
        비교해서 변경된 내용이 있으면, JPA에서 알아서 update쿼리 날림 */
    }

    // "id (name)"에서 id를 추출
    private fun parseId(line: String): Long { // id 니까 Long
        try{
            val endIndex = line.indexOf(" ") - 1 // " "의 위치 찾기
            val id = line.slice(0..endIndex).toLong()

            return id

        }catch (e: Exception){
            throw AdminInternalServerErrorException("ID 추출 중 오류가 발생했습니다.")
        }
    }

    fun deleteProjectSkill(id: Long) {
        projectSkillRepository.deleteById(id)
    }

}