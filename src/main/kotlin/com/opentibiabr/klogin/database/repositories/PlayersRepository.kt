package com.opentibiabr.klogin.database.repositories

import com.opentibiabr.klogin.database.Connector
import com.opentibiabr.klogin.database.entities.Player
import com.opentibiabr.klogin.database.models.players
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.toList

class PlayersRepository : KoinComponent {
    private val connector by inject<Connector>()

    private fun db() = connector.connection

    fun fetchFromLogin(accountId: Int): List<Player> {
        return db().players
            .filter { it.accountId eq accountId }
            .toList()
    }
}