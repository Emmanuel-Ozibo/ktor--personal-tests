package com.example.auth.login

import com.example.database.daos.UserDAO
import com.example.mappers.ResultRowToUserMapper
import com.example.models.response.User
import java.util.UUID
import org.jetbrains.exposed.sql.ResultRow

class LoginRepositoryImpl(
    private val userDAO: UserDAO,
) : LoginRepository {
    override suspend fun findUserByEmail(email: String): ResultRow? {
        return userDAO.findUserByEmail(email)
    }

    override suspend fun getUserWithID(userId: String?): User? {
        return userDAO.fetchUser(UUID.fromString(userId))
    }

    override fun updateToken(userId: String, token: String) {
        TODO("Not yet implemented")
    }
}
