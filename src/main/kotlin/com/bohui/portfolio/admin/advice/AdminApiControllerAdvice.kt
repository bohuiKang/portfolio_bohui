package com.bohui.portfolio.admin.advice

import com.bohui.portfolio.admin.exception.AdminException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AdminApiControllerAdvice {

    val log = LoggerFactory.getLogger(AdminApiControllerAdvice::class.java)

    @ExceptionHandler
    fun handleException(e: AdminException): ResponseEntity<String>{
        log.info(e.message, e) // 오류가 어디에서 발생했는지 라인을 찍어준다

        return ResponseEntity.status(e.httpStatus).body(e.message)
        // 관리자가 http 응답을 구체적으로 관리 가능
    }

    @ExceptionHandler
    fun handleException(e: MethodArgumentNotValidException): ResponseEntity<String>{
        log.info(e.message, e)

        val fieldError = e.bindingResult.fieldErrors[0]
        val message = "[${fieldError.field} ${fieldError.defaultMessage}]"
        // responseBody에 들어갈 메시지를 정리

        return ResponseEntity.badRequest().body(message)
        // 데이터 검증에 실패한 거니까, 클라이언트 오류일 가능성이 높다. => 그래서 클라이언트 오류라고 가정
    }

    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<String>{
        log.info(e.message, e)

        return ResponseEntity.internalServerError().body("시스템 오류가 발생했습니다.")
        // 미리 정의하지 못한 오류이기 때문에, 서버 문제의 가능성이 높아서 internalServerError로 진행
    }
}