package com.opentibiabr.klogin.database

import com.opentibiabr.klogin.config.DatabaseConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.ktorm.database.Database
import org.ktorm.logging.Slf4jLoggerAdapter
import org.slf4j.Logger

class Connector() : KoinComponent {
    private val databaseConfig: DatabaseConfig by inject()
    lateinit var connection: Database

    fun setup(logger: Logger) {
        connection = Database.connect(
            url = databaseConfig.connectionString(),
            user = databaseConfig.user,
            password = databaseConfig.password,
            logger = Slf4jLoggerAdapter(logger),
        )
    }
}