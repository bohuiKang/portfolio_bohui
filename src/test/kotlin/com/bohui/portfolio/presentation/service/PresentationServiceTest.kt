package com.bohui.portfolio.presentation.service

import com.bohui.portfolio.domain.entity.Introduction
import com.bohui.portfolio.domain.entity.Link
import com.bohui.portfolio.presentation.repository.PresentationRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PresentationServiceTest {

    @InjectMocks
    lateinit var presentationService: PresentationService

    @Mock
    lateinit var presentationRepository: PresentationRepository

    val DATA_SIZE = 9

    @Test
    fun testGetIntroductions() {

        // given 결과가 주어지고
        val introductions = mutableListOf<Introduction>()
        for (i in 1..DATA_SIZE) { // 1, 3, 5, 7, 9 -> false / 2, 4, 6, 8 -> true
            val introduction = Introduction(content = "${i}", isActive = i % 2 == 0)
            introductions.add(introduction)
        }

        val activeIntroductions = introductions.filter { introduction ->
            introduction.isActive
        } // it.isActive와 동일

        Mockito.`when`(presentationRepository.getActiveIntroductions())
            .thenReturn(activeIntroductions)

        // when 동작을 했을 때
        val introductionDTOs = presentationService.getIntroductions()

        // then 결과는?
        Assertions.assertThat(introductionDTOs).hasSize(DATA_SIZE / 2)
        for (introductionDTO in introductionDTOs) {
            Assertions.assertThat(introductionDTO.content.toInt()).isEven()
        }
    }

    @Test
    fun testGetLinks() {

        // given
        val links = mutableListOf<Link>()
        for (i in 1..DATA_SIZE) {
            val link = Link(name = "${i}", content = "${i}", isActive = i % 2 != 0)
            links.add(link)
        }
        val activeLinks = links.filter { link -> link.isActive }

        Mockito.`when`(presentationRepository.getActiveLinks())
            .thenReturn(activeLinks)

        // when
        val linkDTOs = presentationService.getLinks()

        // then
        var expectedSize = DATA_SIZE / 2
        if (DATA_SIZE % 2 != 0) {
            expectedSize += 1
        }
        Assertions.assertThat(linkDTOs).hasSize(expectedSize)
        for (linkDTO in linkDTOs) {
            Assertions.assertThat(linkDTO.content.toInt()).isOdd()
        }
    }
}