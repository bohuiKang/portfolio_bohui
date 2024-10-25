package com.bohui.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class Experience( //생성자들
    title: String,
    description: String,
    startYear: Int,
    startMonth: Int,
    endYear: Int?, //nullable: 비어 있으면 진행중인 프로젝트
    endMonth: Int?,
    isActive: Boolean
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id")
    var id: Long? = null

    //필드들
    var title: String = title

    var description: String = description

    var startYear: Int = startYear

    var startMonth: Int = startMonth

    var endYear: Int? = endYear

    var endMonth: Int? = endMonth

    var isActive: Boolean = isActive

    @OneToMany(targetEntity = ExperienceDetail::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "experience_id")
    var details: MutableList<ExperienceDetail> = mutableListOf()

    fun getEndYearMonth() :String {
        if(endYear == null || endMonth == null){
            return "Present"
        }
        return "${endYear}.${endMonth}" //'2024.10'형식으로 문자열 반환
    }

    //생성자를 다 받는다.
    fun update( title: String, description: String, startYear: Int,
                startMonth: Int, endYear: Int?, endMonth: Int?, isActive: Boolean){
        this.title = title //받아온 데이터를 필드에다가 없데이트 해줌
        this.description = description
        this.startYear = startYear
        this.startMonth = startMonth
        this.endYear = endYear
        this.endMonth = endMonth
        this.isActive = isActive
    }

    fun addDetails(details: MutableList<ExperienceDetail>?){
        if (details != null) {
            this.details.addAll(details)
        }
    }
}