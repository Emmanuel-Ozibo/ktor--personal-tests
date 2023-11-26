package com.example.auth.login

import com.example.database.daos.UserDAO
import com.example.models.entities.UserEntity
import com.example.models.response.User
import org.jetbrains.exposed.sql.ResultRow
import java.util.UUID
import org.jetbrains.exposed.sql.update

class LoginRepositoryImpl(
    private val userDAO: UserDAO
) : LoginRepository {
    override suspend fun findUserByEmail(email: String): ResultRow? {
        return userDAO.findUserByEmail(email)
    }

    override suspend fun getUserWithID(userId: String?): User? {
        return userDAO.fetchUser(UUID.fromString(userId))
    }

    override suspend fun updateToken(userId: UUID, token: String): Boolean {
        return UserEntity.update({ UserEntity.id eq userId }) {
            it[UserEntity.token] = token
        } > 0
    }
}
