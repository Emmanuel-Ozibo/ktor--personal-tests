package com.example.routes.user

import com.example.database.daos.dbDaos
import com.example.models.response.Response
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import java.util.UUID

fun Route.getUsers() {
    get {
        val users = dbDaos.userDBDao.fetchUsers()
        call.respond(
            Response.Success(
                data = users
            )
        )
    }

    get("/{id?}") {
        val userId = call.parameters["id"]
        val user = dbDaos.userDBDao.fetchUser(UUID.fromString(userId))

        if (user != null) {
            call.respond(
                Response.Success(data = user)
            )
        }

        if (user == null) {
            call.respond(
                Response.Failure(
                    code = HttpStatusCode.NotFound.value,
                    message = "User not found."
                )
            )
        }
    }
}
