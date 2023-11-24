package com.example.auth.signup

import com.example.models.response.User
import org.jetbrains.exposed.sql.ResultRow

interface SignupRepository {
    suspend fun findUserByEmail(email: String): ResultRow?

    suspend fun saveUser(
        firstName: String, lastName: String, email: String, passwordHash: String,
        token: String
    ): User?
}