package repository

import model.User

interface UserRepository {
    suspend fun create(user: User): Int
    suspend fun findById(id: Int): User?
    suspend fun findByUsername(username: String): User?
    suspend fun findAll(): List<User>
    suspend fun findActiveUsers(): List<User>
    suspend fun update(id: Int, user: User): Boolean
    suspend fun delete(id: Int): Boolean
    suspend fun softDelete(id: Int): Boolean
    suspend fun restore(id: Int): Boolean
}