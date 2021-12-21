package com.opentibiabr.klogin.api.models

import com.opentibiabr.klogin.database.entities.Account

data class Session (
    val sessionKey: String,
    val lastLoginTime: Int = 0,
    val emailCodeRequest: Boolean = false,
    val fpsTracking: Boolean = false,
    val isPremium: Boolean = false,
    val isReturner: Boolean = false,
    val optionTracking: Boolean = false,
    val premiumUntil: Long = 0,
    val returnerNotification: Boolean = false,
    val showRewardNews: Boolean = false,
    val status: String = "active",
    val tournamentTicketPurchaseState: Int = 0,
    val tournamentCyclePhase: Int = 0,
) {
    companion object {
        fun fromAccount(account: Account, lastLogin: Int): Session {
            return Session(
                isPremium = account.premiumDays > 0,
                premiumUntil = account.getPremiumTime(),
                sessionKey = account.sessionKey(),
                lastLoginTime = lastLogin,
            )
        }
    }
}