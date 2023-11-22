package com.example.database.daos

import com.example.database.DatabaseFactory.dbQuery
import com.example.models.User
import com.example.models.Users
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class UserDAOImpl : UserDAO {
    override suspend fun insertUser(firstName: String, lastName: String, email: String) = dbQuery {
        val inserted = Users.insert {
            it[Users.firstName] = firstName
            it[Users.lastName] = lastName
            it[Users.email] = email
        }
        inserted.resultedValues?.singleOrNull()?.let(::mapRowToUser)
    }

    override suspend fun fetchUsers(): List<User> = dbQuery {
        Users.selectAll().map(::mapRowToUser)
    }

    override suspend fun fetchUser(id: UUID): User? = dbQuery {
        Users
            .select { Users.id eq id }
            .map(::mapRowToUser)
            .singleOrNull()
    }

    override suspend fun editUser(id: UUID, firstName: String, lastName: String, email: String) = dbQuery {
        Users.update({ Users.id eq id }) {
            it[Users.firstName] = firstName
            it[Users.lastName] = lastName
            it[Users.email] = email
        } > 0
    }

    override suspend fun deleteUser(id: UUID): Boolean = dbQuery {
        Users.deleteWhere { Users.id eq id } > 0
    }

    private fun mapRowToUser(resultRow: ResultRow): User {
        return User(
            id = resultRow[Users.id].toString(),
            firstName = resultRow[Users.firstName],
            lastName = resultRow[Users.lastName],
            email = resultRow[Users.email]
        )
    }
}