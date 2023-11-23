package com.example.routes.user

import com.example.models.User
import com.example.routes.signup.signupRoute
import io.ktor.server.routing.Route
import io.ktor.server.routing.route

fun Route.userRouting() {
    val userList = mutableListOf<User>()

    route("/users") {
        getUsers()
        modifyUser(userList)
        deleteUser(userList)
    }
}
