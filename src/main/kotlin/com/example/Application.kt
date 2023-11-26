package com.example

import com.example.auth.signup.SignupRepositoryImpl
import com.example.database.DatabaseFactory
import com.example.database.daos.UserDAOImpl
import com.example.mappers.ResultRowToUserMapper
import com.example.plugins.configureAuthentication
import com.example.plugins.configureMonitoring
import com.example.plugins.configureResources
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.plugins.configureStatusPages
import com.example.plugins.configureValidation
import com.example.utils.TokenService
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    val secret = environment.config.property("jwt.secret").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val audience = environment.config.property("jwt.audience").getString()

    val tokenService = TokenService(audience = audience, issuer = issuer, secret = secret)


    DatabaseFactory.init()
    configureResources()
    configureMonitoring()
    configureSerialization()
    configureAuthentication(
        tokenService = tokenService,
        signupRepository = SignupRepositoryImpl(
            userDAO = UserDAOImpl(),
            resultRowToUserMapper = ResultRowToUserMapper()
        ),
        mapper = ResultRowToUserMapper()
    )
    configureStatusPages()
    configureValidation()
    configureRouting(
        tokenService = tokenService
    )
}
