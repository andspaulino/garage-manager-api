package application.dto

import kotlinx.serialization.Serializable

@Serializable
data class CustomerResponse(
    val id: Long,
    val name: String,
    val documentType: String,
    val document: String,
    val phone: String? = null,
    val email: String? = null,
    val createdAt: String
)