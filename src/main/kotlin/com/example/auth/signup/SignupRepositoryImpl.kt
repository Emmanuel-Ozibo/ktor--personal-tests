package com.example.auth.signup

import com.example.database.DatabaseFactory.dbQuery
import com.example.mappers.ResultRowToUserMapper
import com.example.models.entities.UserEntity
import com.example.models.response.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class SignupRepositoryImpl(private val resultRowToUserMapper: ResultRowToUserMapper): SignupRepository {
    override suspend fun findUserByEmail(email: String): ResultRow? = dbQuery {
        UserEntity.select { UserEntity.email eq email }
            .singleOrNull()
    }

    override suspend fun saveUser(
        firstName: String,
        lastName: String,
        email: String,
        passwordHash: String,
        token: String
    ): User? = dbQuery {
        val insertStatement = UserEntity.insert {
            it[UserEntity.firstName] = firstName
            it[UserEntity.lastName] = firstName
            it[UserEntity.email] = firstName
            it[UserEntity.password] = firstName
            it[UserEntity.token] = firstName
        }
        val resultRow = insertStatement.resultedValues?.singleOrNull()
        if (resultRow != null) {
            resultRowToUserMapper.mapFromInput(resultRow)
        } else null
    }

}