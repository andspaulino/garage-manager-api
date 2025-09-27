package repository

import model.User


interface UserRepository {
    suspend fun create(user: User): Long
    suspend fun findById(id: Long): User?
    suspend fun findByUsername(username: String): User?
    suspend fun findAll(): List<User>
    suspend fun findActiveUsers(): List<User>
    suspend fun update(id: Long, user: User): Boolean
    suspend fun delete(id: Long): Boolean
    suspend fun softDelete(id: Long): Boolean
    suspend fun restore(id: Long): Boolean
}