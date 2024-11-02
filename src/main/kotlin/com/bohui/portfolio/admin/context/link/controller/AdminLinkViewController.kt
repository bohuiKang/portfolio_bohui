package com.bohui.portfolio.admin.context.link.controller

import com.bohui.portfolio.admin.context.link.service.AdminLinkService
import com.bohui.portfolio.admin.data.FormElementDTO
import com.bohui.portfolio.admin.data.SelectFormElementDTO
import com.bohui.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/link")
class AdminLinkViewController(
    private val adminLinkService: AdminLinkService
) {

    @GetMapping
    fun link(model: Model): String { // 리턴타입은 String

        // FORM 요소 세팅
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("name", 2),
            TextFormElementDTO("content", 8),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString())) // 옵션즈 true, false
        )
        model.addAttribute("formElements", formElements)

        // 테이블 정보 세팅
        val table = adminLinkService.getLinkTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null) // detail table이 있을수도 없을수도 있어서 null

        // 페이지 속성 세팅
        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Index"), // link 가 index에 있는 페이지이기에
            Pair("pageName", table.name),
            Pair("editable", true),
            Pair("deletable", false),
            Pair("hasDetails", false)
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}