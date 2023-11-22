package com.example.routes.user

import com.example.extensions.doesUserExists
import com.example.extensions.isNotNull
import com.example.extensions.orEmpty
import com.example.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting() {
    val userList = mutableListOf<User>()

    route("/users") {
        getUsers(userList)
        saveUser(userList)
        modifyUser(userList)
        deleteUser(userList)
    }
}