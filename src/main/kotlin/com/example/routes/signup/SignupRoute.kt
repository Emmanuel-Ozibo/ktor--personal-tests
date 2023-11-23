package com.example.routes.signup

import com.example.database.daos.dbDaos
import com.example.models.User
import com.example.models.response.Response
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.signupRoute() {
    post("signup") {
        val user = call.receive<User>()
        val userInserted = dbDaos.userDBDao
            .insertUser(firstName = user.firstName, lastName = user.lastName, email = user.email)

        if (userInserted != null) {
            call.respond(
                Response(
                    code = HttpStatusCode.OK.value,
                    message = "User saved successfully.",
                    data = userInserted
                )
            )
        } else {
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
