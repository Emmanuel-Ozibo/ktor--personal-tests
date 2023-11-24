package com.example.plugins

import com.example.models.response.Response
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.response.respondText

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }

        exception<RequestValidationException>{ call: ApplicationCall, cause ->
            call.respond(
                Response.Failure(
                    code = HttpStatusCode.BadRequest.value,
                    message = cause.message.orEmpty()
                )
            )
        }
    }
}