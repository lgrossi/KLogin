package com.opentibiabr.klogin.config

class DatabaseConfig (
    var host: String = "127.0.0.1",
    var port: Int = 3306,
    var databaseName: String = "canary",
    var user: String = "canary",
    var password: String = "canary",
) {
    fun host(host: String) { this.host = host }
    fun port(port: Int) { this.port = port }
    fun databaseName(databaseName: String) { this.databaseName = databaseName }
    fun user(user: String) { this.user = user }
    fun password(password: String) { this.password = password }

    fun connectionString(): String {
        return "jdbc:mysql://$host:$port/$databaseName"
    }

    operator fun invoke(setup: DatabaseConfig.() -> Unit) {
        setup(this)
    }

    override fun toString(): String {
        return "[Database] $host:$port/$databaseName"
    }
}