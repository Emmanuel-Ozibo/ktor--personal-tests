package com.example.routes.user

import com.example.database.daos.dbDaos
import com.example.extensions.doesUserExists
import com.example.models.User
import com.example.models.response.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.saveUser() {
    post ("/{id?}") {
        val user = call.receive<User>()
        val userInserted = dbDaos.userDBDao.insertUser(firstName = user.firstName, lastName = user.lastName, email = user.email)

        if (userInserted != null) {
            call.respond(
                Response(
                    code = HttpStatusCode.OK.value,
                    message = "User saved successfully.",
                    data = userInserted
                )
            )
        }else {
            call.respond(
                Response(
                    code = HttpStatusCode.BadRequest.value,
                    message = "User not saved.",
                    data = null
                )
            )
        }
    }
}