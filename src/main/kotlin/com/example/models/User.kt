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

const val LETTER_LENGTH = 128

object Users : Table() {
    val id = uuid("id").autoGenerate()
    val firstName = varchar("firstName", LETTER_LENGTH)
    val lastName = varchar("lastName", LETTER_LENGTH)
    val email = varchar("email", LETTER_LENGTH)

    override val primaryKey = PrimaryKey(id)
}
