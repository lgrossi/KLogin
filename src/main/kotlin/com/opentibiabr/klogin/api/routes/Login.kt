package com.opentibiabr.klogin.api.routes

import com.fasterxml.jackson.annotation.JsonProperty
import com.opentibiabr.klogin.services.InvalidCredentialsException
import com.opentibiabr.klogin.services.LoginService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import org.koin.ktor.ext.inject

data class LoginInfo (var email: String = "", val password: String = "", val type: String)
data class LoginError(@JsonProperty("errorMessage") val message: String, @JsonProperty("errorCode") val code: Int = 3)

suspend fun PipelineContext<Unit, ApplicationCall>.login() {
    val loginService by application.inject<LoginService>()

    val payload = call.receive<LoginInfo>()

    if (payload.type != "login") {
        call.respond(HttpStatusCode.NotImplemented)
        return
    }

    try {
        call.respond(loginService.authenticate(payload))
    } catch (e: InvalidCredentialsException) {
        call.respond(LoginError("Account email or password is not correct."))
        return
    }
}
