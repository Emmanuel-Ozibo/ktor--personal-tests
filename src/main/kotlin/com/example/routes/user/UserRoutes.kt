package com.example.routes.user


import com.example.models.User
import io.ktor.server.routing.*

fun Route.userRouting() {
    val userList = mutableListOf<User>()

    route("/users") {
        getUsers()
        saveUser(userList)
        modifyUser(userList)
        deleteUser(userList)
    }
}