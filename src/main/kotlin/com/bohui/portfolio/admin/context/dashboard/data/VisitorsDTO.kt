package com.bohui.portfolio.admin.context.dashboard.data

data class VisitorsDTO(
    val name: String, // 주간 or 오늘 or 총 방문자
    val count: Long, // 숫자
    val color: String, // 카드의 색
    val background: String // 카드의 색
)