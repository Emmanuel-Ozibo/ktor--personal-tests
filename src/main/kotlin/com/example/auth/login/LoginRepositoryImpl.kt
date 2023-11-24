package com.example.auth.login

import com.example.models.entities.User

class LoginRepositoryImpl: LoginRepository {
    override fun findUserByEmail(email: String): User? {
        TODO("Not yet implemented")
    }

    override fun updateToken(userId: String, token: String) {
        TODO("Not yet implemented")
    }

}