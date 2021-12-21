package com.opentibiabr.klogin.database.entities

import org.ktorm.entity.Entity

interface Player : Entity<Player> {
    companion object : Entity.Factory<Player>()

    val id: Int
    val name: String
    val level: Int
    val isMale: Int
    val vocation: Int
    val outfit: Outfit
    val lastLogin: Int
    val account: Account

    interface Outfit : Entity<Outfit> {
        val id: Int
        val head: Int
        val body: Int
        val legs: Int
        val feet: Int
        val addons: Int
    }
}