package com.example.auth.login

import com.example.models.response.User

interface LoginRepository {
    fun findUserByEmail(email: String): User?

    fun updateToken(userId: String, token: String)
}