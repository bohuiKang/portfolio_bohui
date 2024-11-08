package com.bohui.portfolio.admin.context.skill.form

import com.bohui.portfolio.domain.entity.Skill
import jakarta.validation.constraints.NotBlank

data class SkillForm(

    @field:NotBlank(message = "필수값입니다.")
    val name: String,
    @field:NotBlank(message = "필수값입니다.")
    val type: String,
    val isActive: Boolean
) {

    fun toEntity(): Skill {
        return Skill(
            name = this.name,
            type = this.type,
            isActive = this.isActive
        )
    }

    // 엔티티를 수정할 때 사용
    fun toEntity(id:Long): Skill {
        val skill = this.toEntity()
        skill.id = id

        return skill
    }
}