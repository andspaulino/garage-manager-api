package application.dto

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class CustomerResponse(
    val id: Int,
    val name: String,
    val documentType: String,
    val document: String,
    val phone: String? = null,
    val email: String? = null,
    val createdAt: LocalDateTime
)