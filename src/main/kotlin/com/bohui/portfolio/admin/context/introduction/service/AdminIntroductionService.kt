package com.bohui.portfolio.admin.context.introduction.service

import com.bohui.portfolio.admin.data.TableDTO
import com.bohui.portfolio.domain.entity.Achievement
import com.bohui.portfolio.domain.repository.IntroductionRepository
import org.springframework.stereotype.Service

@Service
class AdminIntroductionService(
    private val introductionRepository: IntroductionRepository
) {

    fun getIntroductionTable(): TableDTO {
        val classInfo = Achievement::class
        val entities = introductionRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}