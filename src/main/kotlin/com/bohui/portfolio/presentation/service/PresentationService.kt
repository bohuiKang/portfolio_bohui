package com.bohui.portfolio.presentation.service

import com.bohui.portfolio.presentation.dto.IntroductionDTO
import com.bohui.portfolio.presentation.dto.LinkDTO
import com.bohui.portfolio.presentation.dto.ProjectDTO
import com.bohui.portfolio.presentation.dto.ResumeDTO
import com.bohui.portfolio.presentation.repository.PresentationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PresentationService(
    private val presentationRepository: PresentationRepository
) {
    @Transactional(readOnly = true) // 메인페이지에 사용
    fun getIntroductions(): List<IntroductionDTO> {
        val introductions = presentationRepository.getActiveIntroductions()

        return introductions.map { IntroductionDTO(it) }
    }

    @Transactional(readOnly = true) // 메인페이지에 사용
    fun getLinks(): List<LinkDTO> {
        val links = presentationRepository.getActiveLinks()

        return links.map { LinkDTO(it) }
    }

    @Transactional(readOnly = true) // 이력서 페이지에 사용
    fun getResume(): ResumeDTO {
        val experiences = presentationRepository.getActiveExperiences()
        val achievements = presentationRepository.getActiveAchievements()
        val skills = presentationRepository.getActiveSkills()

        return ResumeDTO(
            experiences = experiences,
            achievements = achievements,
            skills = skills
        )
    }

    @Transactional(readOnly = true) // 프로젝트 페이지에 사용
    fun getProjects(): List<ProjectDTO> {
        val projects = presentationRepository.getActiveProjects()

        return projects.map { ProjectDTO(it) }
    }
}