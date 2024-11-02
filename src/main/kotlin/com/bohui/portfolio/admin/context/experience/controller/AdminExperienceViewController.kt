package com.bohui.portfolio.admin.context.experience.controller

import com.bohui.portfolio.admin.context.experience.service.AdminExperienceService
import com.bohui.portfolio.admin.data.FormElementDTO
import com.bohui.portfolio.admin.data.SelectFormElementDTO
import com.bohui.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/experience")
class AdminExperienceViewController(
    private val adminExperienceService: AdminExperienceService
) {

    @GetMapping
    fun experience(model: Model): String {

        // FORM 요소 세팅
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("title", 4),
            TextFormElementDTO("description", 8),
            SelectFormElementDTO("startYear", 3, (2010..2030).toList()), // 연도 제한
            SelectFormElementDTO("startMonth", 2, (1..12).toList()),
            SelectFormElementDTO("endYear", 3, (2010..2030).toList()),
            SelectFormElementDTO("endMonth", 2, (1..12).toList()),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("formElements", formElements)

        // 상세 FORM 요소 세팅
        val detailFormElements = listOf<FormElementDTO>(
            TextFormElementDTO("content", 10),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("detailFormElements", detailFormElements)

        // 테이블 정보 세팅
        val table = adminExperienceService.getExperienceTable()
        model.addAttribute("table", table)

        // 상세 테이블 정보 세팅
        val detailTable = adminExperienceService.getExperienceDetailTable(null) // 첫 접속시 선택한 id가 없기에 null로 빈 리스트 리턴
        model.addAttribute("detailTable", detailTable)

        // 페이지 속성 세팅
        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Resume"),
            Pair("pageName", table.name),
            Pair("editable", true),
            Pair("deletable", false),
            Pair("hasDetails", true)
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}