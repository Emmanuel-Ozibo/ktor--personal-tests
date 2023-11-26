package com.example.routes.auth

import com.example.auth.login.LoginRepository
import com.example.auth.login.LoginRequest
import com.example.models.entities.UserEntity
import com.example.models.response.Response
import com.example.utils.Auth
import com.example.utils.TokenService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.loginRoute(loginRepository: LoginRepository,
                     tokenService: TokenService) {
    post("login") {
        val loginReq = call.receive<LoginRequest>()
        val user = loginRepository.findUserByEmail(loginReq.email)

        when {
            user == null -> {
                call.respond(
                    status = HttpStatusCode.NotFound,
                    Response.Failure(
                        code = HttpStatusCode.NotFound.value,
                        message = "User not found, consider registering."
                    )
                )
            }

            user[UserEntity.password] == Auth.convertToHash(loginReq.password) -> {
                val token = tokenService.generateToken(email = loginReq.email)
                val response = loginRepository.getUserWithID(user[UserEntity.id].toString())

                loginRepository.updateToken(userId = user[UserEntity.id], token = token)

                call.respond(
                    Response.Success(
                        code = HttpStatusCode.OK.value,
                        message = HttpStatusCode.OK.description,
                        data = response
                    )
                )
            }

            else -> {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    Response.Failure(
                        code = HttpStatusCode.BadRequest.value,
                        message = "User email or password is incorrect."
                    )
                )
            }
        }
    }
}
