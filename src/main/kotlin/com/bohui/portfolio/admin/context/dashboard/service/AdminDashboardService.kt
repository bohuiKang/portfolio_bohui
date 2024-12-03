package com.bohui.portfolio.admin.context.dashboard.service

import com.bohui.portfolio.admin.data.TableDTO
import com.bohui.portfolio.domain.entity.HttpInterface
import com.bohui.portfolio.domain.repository.HttpInterfaceRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime

@Service
class AdminDashboardService(
    private val httpInterfaceRepository: HttpInterfaceRepository
) {
    fun getHttpInterfaceTable(): TableDTO {
        val pageRequest = PageRequest.of(0, 100, Sort.Direction.DESC, "id")

        val classInfo = HttpInterface::class
        val entities = httpInterfaceRepository.findAll(pageRequest).content

        return TableDTO.from(classInfo, entities)
    }

    fun countVisitorsTotal(): Long { // 전체 방문자를 세는 함수
        return httpInterfaceRepository.count()
    }

    fun countVisitorsWeekly(): Long { // 오늘부터 6일 전까지 (총 7일)
        var today = LocalDate.now()
        var startDay = today.minusDays(6)
        return httpInterfaceRepository.countAllByCreatedDateTimeBetween(startDay.atStartOfDay(),
            today.atTime(LocalTime.MAX))
    }
    fun countVisitorsToday(): Long { // 오늘 방문자 수
        var today = LocalDate.now()
        return httpInterfaceRepository.countAllByCreatedDateTimeBetween(today.atStartOfDay(),
            today.atTime(LocalTime.MAX))
    }
}
