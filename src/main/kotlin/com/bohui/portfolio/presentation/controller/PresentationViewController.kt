package com.bohui.portfolio.presentation.controller

import com.bohui.portfolio.domain.constant.SkillType
import com.bohui.portfolio.presentation.service.PresentationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PresentationViewController(
    private val presentationService: PresentationService
) {

    @GetMapping("/test")
    fun test(): String {
        return "test"
    }

    @GetMapping("/") // root
    fun index(model: Model): String { // model이 mvc

        val introductions = presentationService.getIntroductions()
        model.addAttribute("introductions", introductions)

        val links = presentationService.getLinks()
        model.addAttribute("links", links)

        return "presentation/index" //html 파일 찾아라

    }

    @GetMapping("/resume")
    fun resume(model: Model): String {

        val resume = presentationService.getResume()
        model.addAttribute("resume", resume)
        model.addAttribute("skillTypes", SkillType.values())

        return "presentation/resume"
    }

    @GetMapping("/projects")
    fun projects(model: Model): String {

        val projects = presentationService.getProjects()
        model.addAttribute("projects", projects)

        return "presentation/projects"
    }

}
