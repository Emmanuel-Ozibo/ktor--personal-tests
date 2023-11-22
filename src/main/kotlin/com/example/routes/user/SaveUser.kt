package com.example.routes.user

import com.example.extensions.doesUserExists
import com.example.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.saveUser(userList: MutableList<User>) {
    post ("/{id?}") {
        val user = call.receive<User>()
        if (userList.doesUserExists(call.parameters["id"])){
            call.respondText("User already exists", status = HttpStatusCode.Conflict)
        } else {
            userList.add(user)
            call.respondText("User saved successfully", status = HttpStatusCode.Created)
        }
    }
}