package com.bohui.portfolio.admin.context.achievement.form

import com.bohui.portfolio.domain.entity.Achievement
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class AchievementForm(

    @field:NotBlank(message = "필수값입니다.")
    val title: String,
    val description: String,
    val host: String,
    val achievedDate: String,
    val isActive: Boolean

){

    fun toEntity(): Achievement {
        return Achievement(
            title = this.title,
            description = this.description,
            host = this.host,
            achievedDate = LocalDate.parse(this.achievedDate),
            isActive = this.isActive
        )
    }

    // 엔티티를 수정할 때 사용
    fun toEntity(id:Long): Achievement {
        val achievement = this.toEntity()
        achievement.id = id

        return achievement
    }
}