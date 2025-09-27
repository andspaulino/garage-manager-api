package persistence.entity

import enums.DocumentType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object CustomerEntity : Table("customer") {
    val id = long("id").autoIncrement()
    val name = varchar("name", 255)
    val documentType = enumerationByName<DocumentType>("document_type", 2)
    val document = varchar("document", 20).uniqueIndex()
    val phone = varchar("phone", 20).nullable()
    val email = varchar("email", 255).nullable()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").nullable()
    val deletedAt = datetime("deleted_at").nullable()

    override val primaryKey = PrimaryKey(id)
}