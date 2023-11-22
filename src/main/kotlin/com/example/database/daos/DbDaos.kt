package com.example.database.daos

data class DbDaos(
    val userDBDao: UserDAO = UserDAOImpl()
)

val dbDaos = DbDaos()
