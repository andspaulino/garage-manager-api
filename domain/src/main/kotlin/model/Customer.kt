package model

import enums.DocumentType
import java.time.LocalDateTime

data class Customer(
    val id: Int? = null,
    val name: String,
    val documentType: DocumentType,
    val document: String,
    val phone: String? = null,
    val email: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val deletedAt: LocalDateTime? = null
)