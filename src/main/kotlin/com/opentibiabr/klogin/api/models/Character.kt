package com.opentibiabr.klogin.api.models

import com.opentibiabr.klogin.config.GlobalConfig
import com.opentibiabr.klogin.database.entities.Player
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class Character (
    val worldId: Int,
    val isHidden: Boolean,
    val isMainCharacter: Boolean,
    val isMale: Boolean,
    val level: Int,
    val name: String,
    val vocation: String,
    val outfitId: Int,
    val headColor: Int,
    val torsoColor: Int,
    val legsColor: Int,
    val detailColor: Int,
    val addonsFlags: Int,
    val tutorial: Boolean = false,
    val isTournamentParticipant: Boolean = false,
    val remainingDailyTournamentPlayTime: Int = 0,
    val dailyRewardState: Int = 0,
) {
    companion object : KoinComponent {
        private val globalConfig by inject<GlobalConfig>()

        private fun Player.isMale(): Boolean = isMale == 1
        private fun Player.getWorldId(): Int = 1
        private fun Player.getVocation(): String = globalConfig.vocations[vocation]

        fun fromPlayer(player: Player): Character {
            return Character(
                worldId = player.getWorldId(),
                isMale = player.isMale(),
                level = player.level,
                name = player.name,
                vocation = player.getVocation(),
                outfitId = player.outfit.id,
                headColor = player.outfit.head,
                torsoColor = player.outfit.body,
                legsColor = player.outfit.legs,
                detailColor = player.outfit.feet,
                addonsFlags = player.outfit.addons,
                isHidden = false,
                isMainCharacter = false,
            )
        }
    }
}