package com.opentibiabr.klogin.config

import com.lgrossi.ktor.ratelimiting.RateConfig
import java.time.Duration
import kotlin.test.*

class LoginServerConfigTest {
    @Test
    fun defaultProperties() {
        val loginServerConfig = LoginServerConfig()
        assertEquals("127.0.0.1", loginServerConfig.host)
        assertEquals(80, loginServerConfig.httpPort)
        assertEquals(9090, loginServerConfig.rcpPort)
        assertEquals(RateConfig(Duration.ofSeconds(1), 10), loginServerConfig.rateLimit)
    }

    @Test
    fun convertToString() {
        val expectedConnectionString = "[Login Server] http(127.0.0.1:80) rpc(127.0.0.1:9090) 10/1"
        assertEquals(expectedConnectionString, LoginServerConfig().toString())
    }

    @Test
    fun propertiesCanBeSetViaInvocation() {
        val loginServerConfig = LoginServerConfig()
        loginServerConfig {
            host("0.0.0.0")
            httpPort(8080)
            rcpPort(9091)
            rateLimit(100, 60)
        }
        assertEquals("0.0.0.0", loginServerConfig.host)
        assertEquals(8080, loginServerConfig.httpPort)
        assertEquals(9091, loginServerConfig.rcpPort)
        assertEquals(RateConfig(Duration.ofMinutes(1), 100), loginServerConfig.rateLimit)

        val expectedConnectionString = "[Login Server] http(0.0.0.0:8080) rpc(0.0.0.0:9091) 100/60"
        assertEquals(expectedConnectionString, loginServerConfig.toString())
    }
}