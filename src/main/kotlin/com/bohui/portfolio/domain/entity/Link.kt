package com.bohui.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class Link(
    name: String,
    content: String,
    isActive: Boolean
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    var id: Long? = null

    var name: String = name

    var content: String = content

    // is_active 로 DB에 들어간다. (jpa에서 알아서 맵핑할 수 있도록)
    var isActive: Boolean = isActive
}