package com.example.mappers

import com.example.models.entities.UserEntity
import com.example.models.response.User
import org.jetbrains.exposed.sql.ResultRow

class ResultRowToUserMapper : Mapper<ResultRow, User> {
    override fun mapFromInput(input: ResultRow): User {
        return User(
            id = input[UserEntity.id].toString(),
            firstName = input[UserEntity.firstName],
            lastName = input[UserEntity.lastName],
            email = input[UserEntity.email],
            token = input[UserEntity.token]
        )
    }

    override fun mapToInput(output: User): ResultRow {
        TODO("Not yet implemented")
    }
}
