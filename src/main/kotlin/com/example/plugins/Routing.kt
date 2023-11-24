package com.example.plugins

import com.example.auth.signup.SignupRepositoryImpl
import com.example.mappers.ResultRowToUserMapper
import com.example.routes.auth.signupRoute
import com.example.routes.testRoute
import com.example.routes.user.userRouting
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        testRoute()
        userRouting()
        signupRoute(signupRepository = SignupRepositoryImpl(ResultRowToUserMapper()))
    }
}
