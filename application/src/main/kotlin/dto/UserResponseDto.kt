package application.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    val id: Int,
    val username: String,
    val role: String,
    val isActive: Boolean,
    val createdAt: String? = null
)