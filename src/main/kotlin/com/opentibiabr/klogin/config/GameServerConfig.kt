package com.opentibiabr.klogin.config

class GameServerConfig (
    var name: String = "Canary",
    var host: String = "127.0.0.1",
    var port: Int = 7172,
    var location: String = "BRA",
) {
    fun name(name: String) { this.name = name }
    fun host(host: String) { this.host = host }
    fun port(port: Int) { this.port = port }
    fun location(location: String) { this.location = location }

    operator fun invoke(setup: GameServerConfig.() -> Unit) { setup(this) }

    override fun toString(): String {
        return "[Game Server] $name($host:$port), $location"
    }
}