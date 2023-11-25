package com.example.auth.login

import com.example.models.response.User
import org.jetbrains.exposed.sql.ResultRow

interface LoginRepository {
    suspend fun findUserByEmail(email: String): ResultRow?

    suspend fun getUserWithID(userId: String?): User?

    fun updateToken(userId: String, token: String)
}
