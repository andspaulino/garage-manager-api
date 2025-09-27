package io.github.andspaulino.infrastructure.persistence.repository

import io.github.andspaulino.domain.model.User
import io.github.andspaulino.domain.repository.UserRepository
import io.github.andspaulino.infrastructure.persistence.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class UserRepositoryImpl : UserRepository {

    override suspend fun create(user: User): Int = dbQuery {
        UserEntity.insert {
            it[username] = user.username
            it[password] = user.password
            it[role] = user.role
            it[isActive] = user.isActive
        }[UserEntity.id]
    }

    override suspend fun findById(id: Int): User? = dbQuery {
        UserEntity.selectAll()
            .where { UserEntity.id eq id }
            .map {
                User(
                    id = it[UserEntity.id],
                    username = it[UserEntity.username],
                    password = it[UserEntity.password],
                    role = it[UserEntity.role],
                    isActive = it[UserEntity.isActive],
                    createdAt = it[UserEntity.createdAt],
                    updatedAt = it[UserEntity.updatedAt],
                    deletedAt = it[UserEntity.deletedAt]
                )
            }
            .singleOrNull()
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}