package com.example.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.testRoute() {
    get("/") {
        call.respondText("Welcome...", status = HttpStatusCode.OK)
    }
}
