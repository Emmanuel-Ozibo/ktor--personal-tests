package com.example.routes.user

import com.example.extensions.isNotNull
import com.example.models.entities.User
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.patch

fun Route.modifyUser(userList: MutableList<User>) {
    patch("/{id?}") {
        val userId = call.parameters["id"]
        val firstName = call.parameters["firstName"]
        val lastName = call.parameters["lastName"]
        val email = call.parameters["email"]

        val user = userList.find { user -> user.id == userId }
        if (firstName.isNotNull()) {
            user?.firstName = firstName.orEmpty()
        }
        if (lastName.isNotNull()) {
            user?.lastName = lastName.orEmpty()
        }
        if (email.isNotNull()) {
            user?.email = email.orEmpty()
        }

        val userIndex = userList.indexOf(user)

        if (user != null) {
            userList.add(userIndex, user)
            call.respond(user)
        } else {
            call.respondText("User Not Found.", status = HttpStatusCode.BadRequest)
        }
    }
}
