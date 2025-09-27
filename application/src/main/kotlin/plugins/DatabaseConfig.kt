package plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import persistence.entity.UserEntity

fun Application.configureDatabases() {
    val dbConfig = environment.config.config("database")
    
    val database = Database.connect(
        url = dbConfig.property("url").getString(),
        driver = dbConfig.property("driver").getString(),
        user = dbConfig.property("user").getString(),
        password = dbConfig.property("password").getString(),
        databaseConfig = DatabaseConfig {
            if (dbConfig.property("sqlLogger").getString().toBoolean()) {
                sqlLogger = StdOutSqlLogger
            }
            useNestedTransactions = dbConfig.property("useNestedTransactions").getString().toBoolean()
        }
    )

    transaction(database) {
        SchemaUtils.create(UserEntity)
    }
}