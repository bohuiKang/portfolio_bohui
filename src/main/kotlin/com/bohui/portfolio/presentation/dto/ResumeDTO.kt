package com.bohui.portfolio.presentation.dto

import com.bohui.portfolio.domain.entity.Achievement
import com.bohui.portfolio.domain.entity.Experience
import com.bohui.portfolio.domain.entity.Skill
import java.time.format.DateTimeFormatter

class ResumeDTO(
    experiences: List<Experience>,
    achievements: List<Achievement>,
    skills: List<Skill>
) {

    var experiences: List<ExperienceDTO> = experiences.map {
        ExperienceDTO(
            title = it.title,
            description = it.description,
            startYearMonth = "${it.startYear}.${it.startMonth}",
            endYearMonth = it.getEndYearMonth(),
            details = it.details.filter { it.isActive }.map { it.content }
        )
    }

    var achievements: List<AchievementDTO> = achievements.map {
        AchievementDTO(
            title = it.title,
            description = it.description,
            host = it.host,
            achievedDate = it.achievedDate
                ?.format(DateTimeFormatter.ISO_LOCAL_DATE) // yyyy-mm-dd
                ?.replace("-", ".") // '/' -> '.'
        )
    }

    var skills: List<SkillDTO> = skills.map { SkillDTO(it) }
}