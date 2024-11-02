package com.bohui.portfolio.admin.context.introduction.service

import com.bohui.portfolio.admin.data.TableDTO
import com.bohui.portfolio.domain.entity.Introduction
import com.bohui.portfolio.domain.repository.IntroductionRepository
import org.springframework.stereotype.Service

@Service
class AdminIntroductionService(
    private val introductionRepository: IntroductionRepository
) {

    fun getIntroductionTable(): TableDTO {
        val classInfo = Introduction::class
        val entities = introductionRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}