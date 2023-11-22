package com.example.routes.user

import com.example.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteUser(userList: MutableList<User>) {
    delete("/{id?}") {
        val userId = call.parameters["id"]
        val user = userList.find { it.id == userId }
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
