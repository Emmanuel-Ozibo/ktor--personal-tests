package com.example.database.daos

import com.example.database.DatabaseFactory.dbQuery
import com.example.models.entities.UserEntity
import com.example.models.response.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import java.util.UUID

class UserDAOImpl : UserDAO {
    override suspend fun insertUser(firstName: String, lastName: String, email: String) = dbQuery {
        val inserted = UserEntity.insert {
            it[UserEntity.firstName] = firstName
            it[UserEntity.lastName] = lastName
            it[UserEntity.email] = email
        }
        inserted.resultedValues?.singleOrNull()?.let(::mapRowToUser)
    }

    override suspend fun fetchUsers(): List<User> = dbQuery {
        UserEntity.selectAll().map(::mapRowToUser)
    }

    override suspend fun fetchUser(id: UUID): User? = dbQuery {
        UserEntity
            .select { UserEntity.id eq id }
            .map(::mapRowToUser)
            .singleOrNull()
    }

    override suspend fun editUser(id: UUID, firstName: String, lastName: String, email: String) = dbQuery {
        UserEntity.update({ UserEntity.id eq id }) {
            it[UserEntity.firstName] = firstName
            it[UserEntity.lastName] = lastName
            it[UserEntity.email] = email
        } > 0
    }

    override suspend fun deleteUser(id: UUID): Boolean = dbQuery {
        UserEntity.deleteWhere { UserEntity.id eq id } > 0
    }

    override suspend fun findUserByEmail(email: String): ResultRow? = dbQuery {
        UserEntity.select { UserEntity.email eq email }
            .singleOrNull()
    }

    private fun mapRowToUser(resultRow: ResultRow): User {
        return User(
            id = resultRow[UserEntity.id].toString(),
            firstName = resultRow[UserEntity.firstName],
            lastName = resultRow[UserEntity.lastName],
            email = resultRow[UserEntity.email],
            token = ""
        )
    }
}
