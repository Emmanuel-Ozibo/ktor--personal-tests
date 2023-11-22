package com.example.plugins

import com.example.routes.testRoute
import com.example.routes.user.userRouting
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        testRoute()
        userRouting()
    }
}
