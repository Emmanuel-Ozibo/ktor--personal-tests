package com.example.routes.auth

import com.example.auth.signup.SignupRepository
import com.example.auth.signup.SignupRequest
import com.example.models.response.Response
import com.example.utils.Auth
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.signupRoute(signupRepository: SignupRepository) {
    post("signup") {
        val signupReq = call.receive<SignupRequest>()
        val user = signupRepository.findUserByEmail(email = signupReq.email)
        when {
            user == null -> {
                // save user.
                val passwordHash = Auth.convertToHash(signupReq.password)
                val token = "e"

                val savedUser = signupRepository.saveUser(
                    firstName = signupReq.firstName,
                    lastName = signupReq.lastName,
                    email = signupReq.email,
                    passwordHash = passwordHash,
                    token = token
                )

                if (savedUser != null) {
                    call.respond(Response.Success(data = savedUser))
                } else {
                    call.respond(
                        status = HttpStatusCode.InternalServerError,
                        Response.Failure(
                            code = HttpStatusCode.InternalServerError.value,
                            message = "User registration failed, try again later."
                        )
                    )
                }
            }

            else -> {
                call.respond(
                    status = HttpStatusCode.Conflict,
                    Response.Failure(
                        code = HttpStatusCode.Conflict.value,
                        message = "User has already been registered"
                    )
                )
            }
        }
    }
}
