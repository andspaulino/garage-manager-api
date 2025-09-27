package application.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Long,
    val username: String,
    val role: String,
    val isActive: Boolean,
    val createdAt: String? = null
)