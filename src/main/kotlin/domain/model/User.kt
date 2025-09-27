package io.github.andspaulino.domain.model

import io.github.andspaulino.domain.enums.UserRole
import java.time.LocalDateTime

data class User(
    val id: Int? = null,
    val username: String,
    val password: String,
    val role: UserRole,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null
)
