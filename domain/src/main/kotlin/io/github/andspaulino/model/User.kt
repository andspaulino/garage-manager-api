package io.github.andspaulino.model

import io.gi.UserRole
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
