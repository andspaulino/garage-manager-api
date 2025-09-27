package persistence.entity

import enums.UserRole
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object UserEntity : Table("users") {
    val id = long("id").autoIncrement()
    val username = varchar("username", 100).uniqueIndex()
    val password = varchar("password", 255)
    val role = enumerationByName("role", 20, UserRole::class)
    val isActive = bool("is_active").default(true)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").nullable()
    val deletedAt = datetime("deleted_at").nullable()

    override val primaryKey = PrimaryKey(id)
}