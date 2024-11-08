package com.bohui.portfolio.admin.context.experience.service

import com.bohui.portfolio.admin.context.experience.form.ExperienceForm
import com.bohui.portfolio.admin.data.TableDTO
import com.bohui.portfolio.admin.exception.AdminBadRequestException
import com.bohui.portfolio.domain.entity.Experience
import com.bohui.portfolio.domain.entity.ExperienceDetail
import com.bohui.portfolio.domain.repository.ExperienceRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AdminExperienceService(
    private val experienceRepository: ExperienceRepository
) {

    fun getExperienceTable(): TableDTO {
        val classInfo = Experience::class
        val entities = experienceRepository.findAll()

        return TableDTO.from(classInfo, entities, "details") // 제외를 안하면 한셀에 디테일 정보가 다 들어간다.
    }

    fun getExperienceDetailTable(id: Long?): TableDTO {
        val classInfo = ExperienceDetail::class
        val entities = if (id != null) experienceRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수 없습니다.") }
            .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }

    @Transactional
    fun save(form: ExperienceForm) {
        val experienceDetails = form.details
            ?.map { detail -> detail.toEntity() } // null이 아닐경우 map{}
            ?.toMutableList() // null이 아닐경우  toMutableList()
        val experience = form.toEntity()
        experience.addDetails(experienceDetails)

        experienceRepository.save(experience)
    }

    @Transactional
    fun update(id: Long, form: ExperienceForm) {
        val experience = experienceRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수 없습니 다.") }

        experience.update(
            title = form.title,
            description = form.description,
            startYear = form.startYear,
            startMonth = form.startMonth,
            endYear = form.endYear,
            endMonth = form.endMonth,
            isActive = form.isActive
        )

        val detailMap = experience.details.map { it.id to it }.toMap()

        form.details?.forEach {
            val entity = detailMap.get(it.id)
            if (entity != null) {
                entity.update(
                    content = it.content,
                    isActive = it.isActive
                )
            } else {
                experience.details.add(it.toEntity())
            }
        }
    }

}