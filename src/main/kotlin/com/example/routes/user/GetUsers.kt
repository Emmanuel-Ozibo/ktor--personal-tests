package com.example.routes.user

import com.example.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getUsers(userList: MutableList<User>) {
    get {
        call.respond(userList)
    }

    get ("/{id?}") {
        val userId = call.parameters["id"]
        val user = userList.find { it.id == userId }

        if (user != null)
            call.respond(user)

        if (user == null)
            call.respondText("User not found.", status = HttpStatusCode.BadRequest)
    }
}