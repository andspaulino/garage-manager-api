package io.github.andspaulino.plugins

import io.github.andspaulino.infrastructure.persistence.entity.UserEntity
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val database = Database.connect(
        url = "jdbc:postgresql://localhost:5432/garage-manager-api",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "postgres",
        databaseConfig = DatabaseConfig {
            sqlLogger = StdOutSqlLogger
            useNestedTransactions = true
        }
    )

    transaction(database) {
        SchemaUtils.create(UserEntity)
    }
}