package io.github.andspaulino.domain.repository

import io.github.andspaulino.domain.model.User

interface UserRepository {
    suspend fun create(user: User): Int
    suspend fun findById(id: Int): User?
}