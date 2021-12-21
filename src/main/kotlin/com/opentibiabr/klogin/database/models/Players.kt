package com.opentibiabr.klogin.database.models

import com.opentibiabr.klogin.database.entities.Player
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

val Database.players get() = this.sequenceOf(Players)

object Players : Table<Player>("players"){
    val id = int("id").primaryKey().bindTo { it.id }
    val accountId = int("account_id").references(Accounts) { it.account }
    val name = varchar("name").bindTo { it.name }
    val level = int("level").bindTo { it.level }
    val sex = int("sex").bindTo { it.isMale }
    val vocation = int("vocation").bindTo { it.vocation }
    val outfitId = int("looktype").bindTo { it.outfit.id }
    val headColor = int("lookhead").bindTo { it.outfit.head }
    val torsoColor = int("lookbody").bindTo { it.outfit.body }
    val legsColor = int("looklegs").bindTo { it.outfit.legs }
    val detailColor = int("lookfeet").bindTo { it.outfit.feet }
    val addonsFlags = int("lookaddons").bindTo { it.outfit.addons }
    val lastlogin = int("lastlogin").bindTo { it.lastLogin }
}