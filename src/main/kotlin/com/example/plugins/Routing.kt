package com.example.plugins

import com.example.models.User
import com.example.routes.testRoute
import com.example.routes.userRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        testRoute()
        userRouting()
    }
}
