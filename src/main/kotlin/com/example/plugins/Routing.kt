package com.example.plugins

import com.example.auth.login.LoginRepositoryImpl
import com.example.auth.signup.SignupRepositoryImpl
import com.example.database.daos.UserDAOImpl
import com.example.mappers.ResultRowToUserMapper
import com.example.routes.auth.loginRoute
import com.example.routes.auth.signupRoute
import com.example.routes.testRoute
import com.example.routes.user.userRouting
import com.example.utils.TokenService
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting(tokenService: TokenService) {
    routing {
        testRoute()
        userRouting()
        signupRoute(
            signupRepository = SignupRepositoryImpl(
                userDAO = UserDAOImpl(),
                resultRowToUserMapper = ResultRowToUserMapper()
            ),
            tokenService = tokenService
        )
        loginRoute(
            loginRepository = LoginRepositoryImpl(
                userDAO = UserDAOImpl()
            ),
            tokenService = tokenService
        )
    }
}
