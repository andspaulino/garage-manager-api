package io.github.andspaulino.plugins

import io.github.andspaulino.persistence.entity.UserEntity
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val database = Database.connect(
        url = environment.config.property("database.url").getString(),
        user = environment.config.property("database.username").getString(),
        password = environment.config.property("database.password").getString(),
        driver = environment.config.property("database.driver").getString(),
        databaseConfig = DatabaseConfig {
            sqlLogger = StdOutSqlLogger
            useNestedTransactions = true
        }
    )

    transaction(database) {
        SchemaUtils.create(UserEntity)
    }
}