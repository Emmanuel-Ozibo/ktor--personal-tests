package com.example.routes.user

import com.example.models.entities.User
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete

fun Route.deleteUser(userList: MutableList<User>) {
    delete("/{id?}") {
        val userId = call.parameters["id"]
        val user = userList.find { user: User -> user.id == userId }
        if (user == null) {
            call.respondText(
                "User Not Found.",
                status = HttpStatusCode.BadRequest
            )
        } else {
            userList.remove(user)
            call.respond(user)
        }
    }
}
