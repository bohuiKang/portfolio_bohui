package com.bohui.portfolio.domain.repository

import com.bohui.portfolio.domain.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface AccountRepository : JpaRepository<Account, Long> {

    fun findByLoginId(loginId: String): Optional<Account>

}