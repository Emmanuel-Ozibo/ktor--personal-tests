package com.example.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

class TokenService(
    private val audience: String,
    private val issuer: String,
    secret: String
) {
    private val algorithm = Algorithm.HMAC512(secret)

    companion object {
        const val USER_EMAIL = "user_email"
        const val TEN_MINS = 600_000L
    }

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(email: String): String = JWT.create()
        .withAudience(audience)
        .withIssuer(issuer)
        .withClaim(USER_EMAIL, email)
        .withExpiresAt(expiresAt())
        .sign(algorithm)

    private fun expiresAt() = Date(System.currentTimeMillis() + TEN_MINS)
}
