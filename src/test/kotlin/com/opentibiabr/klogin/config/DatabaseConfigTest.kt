package com.opentibiabr.klogin.config

import kotlin.test.*

class DatabaseConfigTest {
    @Test
    fun defaultProperties() {
        val dbConfig = DatabaseConfig()
        assertEquals("127.0.0.1", dbConfig.host)
        assertEquals(3306, dbConfig.port)
        assertEquals("canary", dbConfig.databaseName)
        assertEquals("canary", dbConfig.user)
        assertEquals("canary", dbConfig.password)
    }

    @Test
    fun connectionString() {
        val expectedConnectionString = "jdbc:mysql://127.0.0.1:3306/canary"
        assertEquals(expectedConnectionString, DatabaseConfig().connectionString())
    }

    @Test
    fun propertiesCanBeSetViaInvocation() {
        val dbConfig = DatabaseConfig()
        dbConfig {
            host("0.0.0.0")
            port(3307)
            databaseName("testOt")
            user("testOt")
            password("testOt")
        }
        assertEquals("0.0.0.0", dbConfig.host)
        assertEquals(3307, dbConfig.port)
        assertEquals("testOt", dbConfig.databaseName)
        assertEquals("testOt", dbConfig.user)
        assertEquals("testOt", dbConfig.password)

        val expectedConnectionString = "jdbc:mysql://0.0.0.0:3307/testOt"
        assertEquals(expectedConnectionString, dbConfig.connectionString())
    }
}