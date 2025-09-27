package persistence.repository

import kotlinx.coroutines.Dispatchers
import model.User
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update
import persistence.entity.UserEntity
import repository.UserRepository
import java.time.LocalDateTime

class UserRepositoryImpl : UserRepository {

    override suspend fun create(user: User): Int = dbQuery {
        UserEntity.insert {
            it[UserEntity.username] = user.username
            it[UserEntity.password] = user.password
            it[UserEntity.role] = user.role
            it[UserEntity.isActive] = user.isActive
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

    override suspend fun findByUsername(username: String): User? = dbQuery {
        UserEntity.selectAll()
            .where { UserEntity.username eq username }
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

    override suspend fun findAll(): List<User> = dbQuery {
        UserEntity.selectAll()
            .where { UserEntity.deletedAt.isNull() and (UserEntity.isActive eq true) }
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
    }

    override suspend fun update(id: Int, user: User): Boolean = dbQuery {
        UserEntity.update({ UserEntity.id eq id }) {
            it[UserEntity.username] = user.username
            it[UserEntity.password] = user.password
            it[UserEntity.role] = user.role
            it[UserEntity.isActive] = user.isActive
            it[UserEntity.updatedAt] = LocalDateTime.now()
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        UserEntity.deleteWhere { UserEntity.id eq id } > 0
    }

    override suspend fun softDelete(id: Int): Boolean = dbQuery {
        UserEntity.update({ UserEntity.id eq id }) {
            it[UserEntity.deletedAt] = LocalDateTime.now()
            it[UserEntity.isActive] = false
            it[UserEntity.updatedAt] = LocalDateTime.now()
        } > 0
    }

    override suspend fun findActiveUsers(): List<User> = dbQuery {
        UserEntity.selectAll()
            .where { (UserEntity.isActive eq true) and UserEntity.deletedAt.isNull() }
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
    }

    override suspend fun restore(id: Int): Boolean = dbQuery {
        UserEntity.update({ UserEntity.id eq id }) {
            it[UserEntity.deletedAt] = null
            it[UserEntity.isActive] = true
            it[UserEntity.updatedAt] = LocalDateTime.now()
        } > 0
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}