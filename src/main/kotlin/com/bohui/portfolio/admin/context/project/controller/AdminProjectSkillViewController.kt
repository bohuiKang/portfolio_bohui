package com.bohui.portfolio.admin.context.project.controller

import com.bohui.portfolio.admin.context.project.service.AdminProjectSkillService
import com.bohui.portfolio.admin.data.FormElementDTO
import com.bohui.portfolio.admin.data.SelectFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/project/skill")
class AdminProjectSkillViewController(
    private val adminProjectSkillService: AdminProjectSkillService
) {

    @GetMapping
    fun projectSkill(model: Model): String {

        val projectList = adminProjectSkillService.getProjectList()
        val skillList = adminProjectSkillService.getSkillList()

        // FORM 요소 세팅
        val formElements = listOf<FormElementDTO>(
            SelectFormElementDTO("project", 8, projectList),
            SelectFormElementDTO("skill", 4, skillList),
        )
        model.addAttribute("formElements", formElements)

        // 테이블 정보 세팅
        val table = adminProjectSkillService.getProjectSkillTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        // 페이지 속성 세팅
        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Projects"),
            Pair("pageName", table.name),
            Pair("editable", false),
            Pair("deletable", true),
            Pair("hasDetails", false)
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}
