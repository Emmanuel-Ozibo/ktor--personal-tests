package com.example.database.daos

interface UserDAO {
    suspend fun insertUser(firstName: String, lastName: String, email: String)
}