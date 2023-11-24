package com.example.models.entities

import org.jetbrains.exposed.sql.Table

const val LETTER_LENGTH = 128
const val TOKEN_LENGTH = 256

object UserEntity : Table() {
    val id = uuid("id").autoGenerate()
    val firstName = varchar("firstName", LETTER_LENGTH)
    val lastName = varchar("lastName", LETTER_LENGTH)
    val email = varchar("email", LETTER_LENGTH).uniqueIndex()
    val password = varchar("password", TOKEN_LENGTH)
    val token = varchar("token", TOKEN_LENGTH)

    override val primaryKey = PrimaryKey(id)
}
