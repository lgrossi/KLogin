package com.opentibiabr.klogin

import com.opentibiabr.klogin.config.DatabaseConfig
import com.opentibiabr.klogin.config.GameServerConfig
import com.opentibiabr.klogin.config.GlobalConfig
import com.opentibiabr.klogin.config.LoginServerConfig
import com.opentibiabr.klogin.database.Connector
import com.opentibiabr.klogin.database.repositories.AccountsRepository
import com.opentibiabr.klogin.database.repositories.PlayersRepository
import com.opentibiabr.klogin.services.LoginService
import org.koin.dsl.module

val configModule = module(createdAtStart = true) {
    single { DatabaseConfig() }
    single { GameServerConfig() }
    single { LoginServerConfig() }
    single { GlobalConfig(get(), get(), get()) }
}

val mainModule = module(createdAtStart = true) {
    single { Connector() }
    single { AccountsRepository() }
    single { PlayersRepository() }
    single { LoginService() }
}
