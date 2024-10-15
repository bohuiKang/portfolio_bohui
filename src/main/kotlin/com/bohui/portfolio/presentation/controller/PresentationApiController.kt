package com.bohui.portfolio.presentation.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PresentationApiController {

    //테스트용 컨트롤러, return한 객체를 바로 body에 넣어준다.
    @GetMapping("/test")
    fun test(): String {
        return "OK"
    }

}