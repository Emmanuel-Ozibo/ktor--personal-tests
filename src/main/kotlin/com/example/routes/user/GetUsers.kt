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
            Response(
                code = HttpStatusCode.OK.value,
                message = HttpStatusCode.OK.description,
                data = users
            )
        )
    }

    get("/{id?}") {
        val userId = call.parameters["id"]
        val user = dbDaos.userDBDao.fetchUser(UUID.fromString(userId))

        if (user != null) {
            call.respond(
                Response(
                    code = HttpStatusCode.OK.value,
                    message = HttpStatusCode.OK.description,
                    data = user
                )
            )
        }

        if (user == null) {
            call.respond(
                Response(
                    code = HttpStatusCode.NotFound.value,
                    message = "User not found.",
                    data = null
                )
            )
        }
    }
}
