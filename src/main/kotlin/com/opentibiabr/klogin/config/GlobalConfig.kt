package com.opentibiabr.klogin.config

import com.opentibiabr.klogin.tools.KtsLoader
import org.slf4j.Logger
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.reflect.KProperty1
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

class GlobalConfig (
    val gameServerConfig: GameServerConfig,
    val loginServerConfig: LoginServerConfig,
    val databaseConfig: DatabaseConfig,
    val vocations: VocationsSet = VocationsSet(),
) {
    private val engine = KtsLoader.engine()
    private val defaultConfigPath: Path = Paths.get("config.kts")

    init {
        getConfigs().forEach { property ->
            engine.put(property.name, property.getter.call(this))
        }

        engine.eval(Files.newBufferedReader(defaultConfigPath))
    }

    fun log(log: Logger) {
        log.info(gameServerConfig.toString())
        log.info(loginServerConfig.toString())
        log.info(databaseConfig.toString())
        log.info(vocations.toString())
    }

    private fun getConfigs(): Collection<KProperty1<out GlobalConfig, *>> {
        return this::class.memberProperties.filter { it.visibility?.equals(KVisibility.PUBLIC) ?: false }
    }

    class VocationsSet {
        private val vocations: MutableList<String> = mutableListOf()

        fun vocations() = vocations
        fun vocation(vocation: String) = vocations.add(vocation)

        operator fun get(index: Int): String {
            return vocations[index]
        }

        operator fun invoke(setup: VocationsSet.() -> Unit) {
            setup(this)
        }

        override fun toString(): String {
            return "[Vocations] ".plus(vocations.joinToString(separator = ", "))
        }
    }
}
