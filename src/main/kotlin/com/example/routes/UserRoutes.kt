package com.example.routes

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

        get {
            call.respond(userList)
        }

        post ("/{id?}") {
            val user = call.receive<User>()
            if (userList.doesUserExists(call.parameters["id"])){
                call.respondText("User already exists", status = HttpStatusCode.Conflict)
            } else {
                userList.add(user)
                call.respondText("User saved successfully", status = HttpStatusCode.Created)
            }
        }

        get ("/{id?}") {
            val userId = call.parameters["id"]
            val user = userList.find { it.id == userId }

            if (user != null)
                call.respond(user)

            if (user == null)
                call.respondText("User not found.", status = HttpStatusCode.BadRequest)
        }

        patch("/{id?}") {
            val userId = call.parameters["id"]
            val firstName = call.parameters["firstName"]
            val lastName = call.parameters["lastName"]
            val email = call.parameters["email"]

            val user = userList.find { it.id == userId }
            if (firstName.isNotNull())
                user?.firstName = firstName.orEmpty()
            if (lastName.isNotNull())
                user?.lastName = lastName.orEmpty()
            if (email.isNotNull())
                user?.email = email.orEmpty()

            val userIndex = userList.indexOf(user)

            if (user != null) {
                userList.add(userIndex, user)
                call.respond(user)
            } else {
                call.respondText("User Not Found.", status = HttpStatusCode.BadRequest)
            }

        }

        delete("/{id?}") {
            val userId = call.parameters["id"]
            val user = userList.find { it.id ==  userId}
            if (user == null) {
                call.respondText("User Not Found.",
                    status = HttpStatusCode.BadRequest)
            } else {
                userList.remove(user)
                call.respond(user)
            }
        }

    }
}