package com.bohui.portfolio.presentation.controller

import com.bohui.portfolio.presentation.dto.IntroductionDTO
import com.bohui.portfolio.presentation.dto.LinkDTO
import com.bohui.portfolio.presentation.dto.ProjectDTO
import com.bohui.portfolio.presentation.dto.ResumeDTO
import com.bohui.portfolio.presentation.service.PresentationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PresentationApiController(
    private val presentationService: PresentationService
) {

    //테스트용 컨트롤러, return한 객체를 바로 body에 넣어준다.
    @GetMapping("/test")
    fun test(): String {
        return "OK"
    }

    @GetMapping("/v1/introductions")
    fun getIntroductions(): List<IntroductionDTO> {
        return presentationService.getIntroductions()
    }

    @GetMapping("/v1/links")
    fun getLinks(): List<LinkDTO> {
        return presentationService.getLinks()
    }

    @GetMapping("/v1/resume")
    fun getResume(): ResumeDTO {
        return presentationService.getResume()
    }

    @GetMapping("/v1/projects")
    fun getProjects(): List<ProjectDTO> {
        return presentationService.getProjects()
    }

}