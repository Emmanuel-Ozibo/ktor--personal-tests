package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User(
    val id: String,
    var firstName: String,
    var lastName: String,
    var email: String
)

object Users : Table() {
    val id = uuid("id").autoGenerate()
    val firstName = varchar("firstName", 128)
    val lastName = varchar("lastName", 128)
    val email = varchar("email", 128)

    override val primaryKey = PrimaryKey(id)
}
