package com.opentibiabr.klogin.api.routes

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/login") { login() }
        post("/login.php") { login() }
    }
}
