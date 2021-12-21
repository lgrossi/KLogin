package com.opentibiabr.klogin.config

import kotlin.test.*

class GameServerConfigTest {
    @Test
    fun defaultProperties() {
        val gameServerConfig = GameServerConfig()
        assertEquals("Canary", gameServerConfig.name)
        assertEquals("127.0.0.1", gameServerConfig.host)
        assertEquals(7172, gameServerConfig.port)
        assertEquals("BRA", gameServerConfig.location)
    }

    @Test
    fun convertToString() {
        val expectedConnectionString = "[Game Server] Canary(127.0.0.1:7172), BRA"
        assertEquals(expectedConnectionString, GameServerConfig().toString())
    }

    @Test
    fun propertiesCanBeSetViaInvocation() {
        val gameServerConfig = GameServerConfig()
        gameServerConfig {
            name("testOt")
            host("0.0.0.0")
            port(7173)
            location("NL")
        }
        assertEquals("testOt", gameServerConfig.name)
        assertEquals("0.0.0.0", gameServerConfig.host)
        assertEquals(7173, gameServerConfig.port)
        assertEquals("NL", gameServerConfig.location)

        val expectedConnectionString = "[Game Server] testOt(0.0.0.0:7173), NL"
        assertEquals(expectedConnectionString, gameServerConfig.toString())
    }
}