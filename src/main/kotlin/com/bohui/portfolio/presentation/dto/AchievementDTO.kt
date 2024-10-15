package com.bohui.portfolio.presentation.dto

data class AchievementDTO(
    val title: String,
    val description: String,
    val host: String,
    val achievedDate: String? // 클라이언트
)