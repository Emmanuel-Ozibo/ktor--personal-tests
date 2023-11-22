package com.example.database.daos

import com.example.models.User
import java.util.UUID

interface UserDAO {
    suspend fun insertUser(firstName: String, lastName: String, email: String): User?
    suspend fun fetchUsers(): List<User>
    suspend fun fetchUser(id: UUID): User?
    suspend fun editUser(id: UUID, firstName: String, lastName: String, email: String): Boolean
    suspend fun deleteUser(id: UUID): Boolean
}