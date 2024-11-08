package com.bohui.portfolio.admin.context.link.form

import com.bohui.portfolio.domain.entity.Link
import jakarta.validation.constraints.NotBlank

data class LinkForm(

    @field:NotBlank(message = "필수값입니다.")
    val name: String,
    @field:NotBlank(message = "필수값입니다.")
    val content: String,
    val isActive: Boolean
) {

    fun toEntity(): Link {
        return Link(
            name = this.name,
            content = this.content,
            isActive = this.isActive
        )
    }

    // 엔티티를 수정할 때 사용
    fun toEntity(id:Long): Link {
        val link = this.toEntity()
        link.id = id

        return link
    }
}