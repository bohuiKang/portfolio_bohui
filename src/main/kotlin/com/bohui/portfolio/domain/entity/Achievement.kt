package com.bohui.portfolio.domain.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Achievement(
    title: String,
    description: String,
    achievedDate: LocalDate?, //null ok
    host: String,
    isActive: Boolean //기본생성자
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    var id: Long? = null

    var title: String = title

    var description: String = description

    var achievedDate: LocalDate? = achievedDate

    var host: String = host

    var isActive: Boolean = isActive //다섯개의 필드

}