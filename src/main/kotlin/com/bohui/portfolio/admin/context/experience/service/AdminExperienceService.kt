package com.bohui.portfolio.admin.context.experience.service

import com.bohui.portfolio.admin.data.TableDTO
import com.bohui.portfolio.admin.exception.AdminBadRequestException
import com.bohui.portfolio.domain.entity.Experience
import com.bohui.portfolio.domain.entity.ExperienceDetail
import com.bohui.portfolio.domain.repository.ExperienceRepository
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
}
