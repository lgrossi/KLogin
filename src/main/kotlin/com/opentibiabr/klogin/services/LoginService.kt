package com.opentibiabr.klogin.services

import com.opentibiabr.klogin.api.models.*
import com.opentibiabr.klogin.api.models.World.Companion.getWorlds
import com.opentibiabr.klogin.api.routes.LoginInfo
import com.opentibiabr.klogin.database.repositories.AccountsRepository
import com.opentibiabr.klogin.database.repositories.PlayersRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class PlayData(val characters: List<Character>, var worlds: List<World>)
data class LoginSession(val playData: PlayData, val session: Session)

class InvalidCredentialsException(message: String? = null) : Exception(message)

class LoginService : KoinComponent {
    private val accountsRepository by inject<AccountsRepository>()
    private val playersRepository by inject<PlayersRepository>()

    fun authenticate(payload: LoginInfo): LoginSession {
        val account = accountsRepository.fetchFromLogin(payload)
            ?: throw InvalidCredentialsException("Account email or password is not correct.")

        val players = playersRepository.fetchFromLogin(account.id)


        return LoginSession(
            playData = PlayData(
                characters = players.map { Character.fromPlayer(it) },
                worlds = getWorlds()
            ),
            session = Session.fromAccount(account, players.maxByOrNull { it.lastLogin }?.lastLogin ?: 0)
        )
    }
}