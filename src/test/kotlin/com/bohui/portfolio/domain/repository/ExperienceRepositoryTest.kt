package com.bohui.portfolio.domain.repository

import com.bohui.portfolio.domain.entity.Experience
import com.bohui.portfolio.domain.entity.ExperienceDetail
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExperienceRepositoryTest(
    @Autowired val experienceRepository: ExperienceRepository // 테스트할 대상을 주입받음
) {

    val DATA_SIZE = 10 //테스트 데이트를 초기화할 때, 나중에 결과 검증할 떄 쓰임

    // 비어있는 더미 객체(entity) 생성
    private fun createExperience(n: Int): Experience { // return type은 Experience

        val experience = Experience(
            title = "${n}",
            description = "테스트 설명 ${n}",
            startYear = 2023,
            startMonth = 9,
            endYear = 2023,
            endMonth = 9,
            isActive = true
        )

        val details = mutableListOf<ExperienceDetail>()
        for (i in 1..n) { //1..n
            val experienceDetail = ExperienceDetail(content = "테스트 ${i}", isActive = true)
            details.add(experienceDetail)
        }
        experience.addDetails(details)
        return experience
    }

    // 테스트 데이터 초기화
    @BeforeAll
    fun beforeAll() {
        println("----- 데이터 초기화 이전 조회 시작 -----")
        val beforeInitialize = experienceRepository.findAll()
        assertThat(beforeInitialize).hasSize(0) // 테스트를 검증하는 메소드
        println("----- 데이터 초기화 이전 조회 종료 -----")
        println("----- 테스트 데이터 초기화 시작 -----")
        val experiences = mutableListOf<Experience>()
        for (i in 1..DATA_SIZE) {
            val experience = createExperience(i)
            experiences.add(experience)
        }
        experienceRepository.saveAll(experiences)
        println("----- 테스트 데이터 초기화 종료 -----")
    }

    @Test
    fun testFindAll() {
        println("----- findAll 테스트 시작 -----")
        val experiences = experienceRepository.findAll()
        assertThat(experiences).hasSize(DATA_SIZE)
        println("experiences.size: ${experiences.size}")
        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
            println("experience.details.size: ${experience.details.size}")
        }
        println("----- findAll 테스트 종료 -----")
    }

    @Test
    fun testFindAllByIsActive() {
        println("----- findAllByIsActive 테스트 시작 -----")
        val experiences = experienceRepository.findAllByIsActive(true)
        assertThat(experiences).hasSize(DATA_SIZE)
        println("experiences.size: ${experiences.size}")
        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
            println("experience.details.size: ${experience.details.size}")
        }
        println("----- findAllByIsActive 테스트 종료 -----")
    }

}