package com.example.auth.signup

import com.example.database.DatabaseFactory.dbQuery
import com.example.database.daos.UserDAO
import com.example.mappers.ResultRowToUserMapper
import com.example.models.entities.UserEntity
import com.example.models.response.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert

class SignupRepositoryImpl(
    private val userDAO: UserDAO,
    private val resultRowToUserMapper: ResultRowToUserMapper
) : SignupRepository {

    override suspend fun findUserByEmail(email: String): ResultRow? =
        userDAO.findUserByEmail(email)

    override suspend fun saveUser(
        firstName: String,
        lastName: String,
        email: String,
        passwordHash: String,
        token: String
    ): User? = dbQuery {
        val insertStatement = UserEntity.insert {
            it[UserEntity.firstName] = firstName
            it[UserEntity.lastName] = lastName
            it[UserEntity.email] = email
            it[UserEntity.password] = passwordHash
            it[UserEntity.token] = token
        }
        val resultRow = insertStatement.resultedValues?.singleOrNull()
        if (resultRow != null) {
            resultRowToUserMapper.mapFromInput(resultRow)
        } else {
            null
        }
    }
}
