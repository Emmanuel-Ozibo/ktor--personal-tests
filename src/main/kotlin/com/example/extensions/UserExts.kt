package com.example.extensions

import com.example.models.User

fun List<User>.doesUserExists(userId: String?): Boolean {
    val user = this.find { user -> user.id == userId }
    return user != null
}
