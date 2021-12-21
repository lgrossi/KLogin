package com.opentibiabr.klogin.api.models

import com.opentibiabr.klogin.config.GameServerConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class World (
    val id: Int,
    val externalAddress: String,
    val externalAddressProtected: String,
    val externalAddressUnprotected: String,
    val externalPort: Int,
    val externalPortProtected: Int,
    val externalPortUnprotected: Int,
    val location: String,
    val name: String,
    val AntiCheatProtection: Boolean = false,
    val CurrentTournamentPhase: Int = 0,
    val IsTournamentWorld: Boolean = false,
    val PreviewState: Int = 0,
    val PvpType: Int = 0,
    var RestrictedStore: Boolean = false,
) {
    companion object : KoinComponent {
        private val gameServerConfig by inject<GameServerConfig>()

        fun getWorlds(): List<World> {
            return listOf(
                World(
                    id = 1,
                    externalAddress = gameServerConfig.host,
                    externalAddressProtected = gameServerConfig.host,
                    externalAddressUnprotected = gameServerConfig.host,
                    externalPort = gameServerConfig.port,
                    externalPortProtected = gameServerConfig.port,
                    externalPortUnprotected = gameServerConfig.port,
                    location = gameServerConfig.location,
                    name = gameServerConfig.name,
                )
            )
        }
    }
}