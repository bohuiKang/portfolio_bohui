package com.bohui.portfolio.admin.context.link.service

import com.bohui.portfolio.admin.data.TableDTO
import com.bohui.portfolio.domain.entity.Achievement
import com.bohui.portfolio.domain.repository.LinkRepository
import org.springframework.stereotype.Service

@Service
class AdminLinkService(
    private val linkRepository: LinkRepository
) {

    fun getLinkTable(): TableDTO {
        val classInfo = Achievement::class
        val entities = linkRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}