package com.opentibiabr.klogin.database.entities

import com.opentibiabr.klogin.database.models.Accounts
import org.ktorm.entity.Entity
import java.time.Duration
import java.time.Instant

interface Account : Entity<Account> {
    companion object : Entity.Factory<Account>()

    val id: Int
    val name: String
    val password: String
    val email: String
    val premiumDays: Int

    fun getPremiumTime(): Long {
        if (premiumDays <= 0) return 0
        return Instant.now().epochSecond + Duration.ofDays(premiumDays.toLong()).toSeconds()
    }

    fun sessionKey(): String = "${Accounts.email}\n${Accounts.password}"
}