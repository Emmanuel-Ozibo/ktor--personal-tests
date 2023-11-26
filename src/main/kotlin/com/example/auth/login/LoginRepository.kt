package com.example.auth.login

import com.example.models.response.User
import org.jetbrains.exposed.sql.ResultRow
import java.util.UUID

interface LoginRepository {
    suspend fun findUserByEmail(email: String): ResultRow?

    suspend fun getUserWithID(userId: String?): User?

    suspend fun updateToken(userId: UUID, token: String): Boolean
}
