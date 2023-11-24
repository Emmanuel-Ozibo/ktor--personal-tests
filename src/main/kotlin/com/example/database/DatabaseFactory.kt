package com.example.database

import com.example.models.entities.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:~/test"
        val database = Database.connect(jdbcURL, driverClassName, user = "sa")
        transaction(database) {
            SchemaUtils.create(UserEntity)
        }
    }

    suspend fun <T> dbQuery(dispatcher: CoroutineDispatcher = Dispatchers.IO, block: suspend () -> T): T =
        newSuspendedTransaction(dispatcher) { block() }
}
