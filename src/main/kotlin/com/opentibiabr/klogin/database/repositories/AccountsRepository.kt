package com.opentibiabr.klogin.database.repositories

import com.opentibiabr.klogin.api.routes.LoginInfo
import com.opentibiabr.klogin.database.Connector
import com.opentibiabr.klogin.database.models.accounts
import com.opentibiabr.klogin.database.entities.Account
import com.opentibiabr.klogin.tools.extensions.sha1
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.firstOrNull

class AccountsRepository : KoinComponent {
    private val connector by inject<Connector>()

    private fun db() = connector.connection

    fun fetchFromLogin(loginInfo: LoginInfo): Account? {
        return db().accounts
            .filter { it.email eq loginInfo.email }
            .filter { it.password eq loginInfo.password.sha1() }
            .firstOrNull()
    }
}