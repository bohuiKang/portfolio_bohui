package com.bohui.portfolio.admin.context.introduction.form

import com.bohui.portfolio.domain.entity.Introduction
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class IntroductionForm(

    @field:NotBlank(message = "필수값입니다.")
    val content: String,
    val isActive: Boolean

) {
    
    fun toEntity(): Introduction {
        return Introduction(
            content = this.content,
            isActive = this.isActive
        )
    }

    // 엔티티를 수정할 때 사용
    fun toEntity(id:Long): Introduction {
        val introduction = this.toEntity()
        introduction.id = id

        return introduction
    }
}