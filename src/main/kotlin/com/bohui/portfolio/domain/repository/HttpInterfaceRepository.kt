package com.bohui.portfolio.domain.repository

import com.bohui.portfolio.domain.entity.HttpInterface
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface HttpInterfaceRepository : JpaRepository<HttpInterface, Long> {

    // 날짜별, 기간별 방문자 수 확인
    fun countAllByCreatedDateTimeBetween(startDate: LocalDateTime, endDate: LocalDateTime): Long
}