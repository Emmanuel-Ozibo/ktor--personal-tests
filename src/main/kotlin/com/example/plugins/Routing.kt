package com.example.plugins

import com.example.routes.testRoute
import com.example.routes.user.userRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        testRoute()
        userRouting()
    }
}
