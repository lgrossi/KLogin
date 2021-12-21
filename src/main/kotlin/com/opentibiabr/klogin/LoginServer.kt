package com.opentibiabr.klogin

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.lgrossi.ktor.ratelimiting.RateLimiting
import com.opentibiabr.klogin.config.GlobalConfig
import com.opentibiabr.klogin.api.routes.configureRouting
import com.opentibiabr.klogin.database.Connector
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.ktorm.jackson.KtormModule

class LoginServer : KoinComponent {
    private val globalConfig: GlobalConfig by inject()
    private val database: Connector by inject()

    fun run() {
        embeddedServer(Netty, port = globalConfig.loginServerConfig.httpPort, host = globalConfig.loginServerConfig.host) {
            database.setup(environment.log)

            configureRouting()

            install(CallLogging)

            install(RateLimiting) {
                limit(globalConfig.loginServerConfig.rateLimit.limit)
                duration(globalConfig.loginServerConfig.rateLimit.duration)
            }

            install(CORS)

            install(ContentNegotiation) {
                jackson {
                    enable(SerializationFeature.INDENT_OUTPUT)
                    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    propertyNamingStrategy = PropertyNamingStrategies.LowerCaseStrategy()
                    registerModule(KtormModule())
                }
            }

            globalConfig.log(environment.log)
        }.start(wait = true)
    }
}

fun main() {
    startKoin {
        modules(configModule, mainModule)
    }

    LoginServer().run()
}
