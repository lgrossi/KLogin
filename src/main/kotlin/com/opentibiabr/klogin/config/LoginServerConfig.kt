package com.opentibiabr.klogin.config

import com.lgrossi.ktor.ratelimiting.RateConfig
import java.time.Duration

class LoginServerConfig (
    var host: String = "127.0.0.1",
    var httpPort: Int = 80,
    var rcpPort: Int = 9090,
    var rateLimit: RateConfig = RateConfig(Duration.ofSeconds(1), 10)
) {
    fun host(host: String) { this.host = host }
    fun httpPort(httpPort: Int) { this.httpPort = httpPort }
    fun rcpPort(rcpPort: Int) { this.rcpPort = rcpPort }
    fun rateLimit(limit: Long, durationInSeconds: Long) {
        this.rateLimit = RateConfig(Duration.ofSeconds(durationInSeconds), limit)
    }

    operator fun invoke(setup: LoginServerConfig.() -> Unit) {
        setup(this)
    }

    override fun toString(): String {
        return "[Login Server] http($host:$httpPort) rpc($host:$rcpPort) ${rateLimit.limit}/${rateLimit.duration.seconds}"
    }
}