package com.example.plugins

import com.example.auth.signup.SignupRepository
import com.example.mappers.ResultRowToUserMapper
import com.example.models.response.Response
import com.example.utils.TokenService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.bearer
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond

fun Application.configureAuthentication(
    tokenService: TokenService,
    userRepository: SignupRepository,
    mapper: ResultRowToUserMapper
) {
    val myRealm = environment.config.property("jwt.realm").getString()

    install(Authentication) {
        jwt("jwt-auth") {
            // Configure jwt authentication
            realm = myRealm
            verifier(tokenService.verifier)
            validate { jwtCred ->
                val payload = jwtCred.payload
                val userEmail = payload.getClaim(TokenService.USER_EMAIL).asString()
                val userResultRow = userRepository.findUserByEmail(userEmail)
                if (userResultRow != null)
                    mapper.mapFromInput(userResultRow)
                else null
            }

            challenge { _, _ ->
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    Response.Failure(
                        code = HttpStatusCode.Unauthorized.value,
                        message = "Token is not valid or has expired"
                    )
                )
            }


        }
    }
}
