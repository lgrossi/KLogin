package com.opentibiabr.klogin.database.models

import com.opentibiabr.klogin.database.entities.Account
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

val Database.accounts get() = this.sequenceOf(Accounts)

object Accounts : Table<Account>("accounts"){
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val password = varchar("password").bindTo { it.password }
    val email = varchar("email").bindTo { it.email }
    val premiumDays = int("premdays").bindTo { it.premiumDays }
}