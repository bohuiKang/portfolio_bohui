package com.bohui.portfolio.admin.data

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ApiResponse<T>(status: HttpStatus) : ResponseEntity<T>(status) {
    
    companion object{ // 안에서 정의되는 메소드는 스태틱 메소드
        fun successCreate(): ResponseEntity<Any>{
            return ResponseEntity.ok("데이터가 저장되었습니다.")
        }

        fun successUpdate(): ResponseEntity<Any>{
            return ResponseEntity.ok("데이터가 수정되었습니다.")
        }

        fun successDelete(): ResponseEntity<Any>{
            return ResponseEntity.ok("데이터가 삭제되었습니다.")
        }
    }
}