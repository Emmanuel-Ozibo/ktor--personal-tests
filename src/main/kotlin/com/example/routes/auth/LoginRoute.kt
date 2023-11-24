package com.example.routes.auth
/*
import com.example.auth.login.LoginRepository
import com.example.auth.login.LoginRequest
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.loginRoute(loginRepository: LoginRepository) {
    post("login") {
        val loginReq = call.receive<LoginRequest>()

        when {
            loginReq.email.isEmpty() -> {
                // send failure
            }

            loginReq.password.isEmpty() -> {
                // send failure
            }

            else -> {
                val user = loginRepository.findUserByEmail(loginReq.email)
                if (user != null) {
                    if (loginReq.password == user.password) {

                    } else {

                    }
                } else {
                    // send failure
                }
            }
        }
    }
}
*/
