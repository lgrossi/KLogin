package com.opentibiabr.klogin.config

import kotlin.test.*

class GlobalConfigTest {
    @Test
    fun vocationSetCanBeIncreaseViaInvocationFunction() {
        val vocations = GlobalConfig.VocationsSet()
        val expectedVocations = mutableListOf("my_vocation_1", "my_vocation_2")

        vocations {
            expectedVocations.forEach{
                vocation(it)
            }
        }

        assertEquals(2, vocations.vocations().size)
        assertEquals(expectedVocations, vocations.vocations())
    }

    @Test
    fun vocationSetToStringIsProperlyBuilt() {
        val vocations = GlobalConfig.VocationsSet()

        vocations {
            mutableListOf("my_vocation_1", "my_vocation_2").forEach{
                vocation(it)
            }
        }

        assertEquals("[Vocations] my_vocation_1, my_vocation_2", vocations.toString())
    }
}