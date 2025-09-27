package persistence.repository

import kotlinx.coroutines.Dispatchers
import model.Customer
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update
import persistence.entity.CustomerEntity
import repository.CustomerRepository
import java.time.LocalDateTime

class CustomerRepositoryImpl : CustomerRepository {

    override suspend fun create(customer: Customer): Int = dbQuery {
        CustomerEntity.insert {
            it[name] = customer.name
            it[documentType] = customer.documentType
            it[document] = customer.document
            it[phone] = customer.phone
            it[email] = customer.email
        }[CustomerEntity.id]
    }

    override suspend fun findById(id: Int): Customer? = dbQuery {
        CustomerEntity.selectAll()
            .where { CustomerEntity.id eq id }
            .singleOrNull()
            ?.let {
                Customer(
                    id = it[CustomerEntity.id],
                    name = it[CustomerEntity.name],
                    documentType = it[CustomerEntity.documentType],
                    document = it[CustomerEntity.document],
                    phone = it[CustomerEntity.phone],
                    email = it[CustomerEntity.email],
                    createdAt = it[CustomerEntity.createdAt],
                    updatedAt = it[CustomerEntity.updatedAt] ?: it[CustomerEntity.createdAt],
                    deletedAt = it[CustomerEntity.deletedAt]
                )
            }
    }

    override suspend fun findByDocument(document: String): Customer? = dbQuery {
        CustomerEntity.selectAll()
            .where { CustomerEntity.document eq document }
            .singleOrNull()
            ?.let {
                Customer(
                    id = it[CustomerEntity.id],
                    name = it[CustomerEntity.name],
                    documentType = it[CustomerEntity.documentType],
                    document = it[CustomerEntity.document],
                    phone = it[CustomerEntity.phone],
                    email = it[CustomerEntity.email],
                    createdAt = it[CustomerEntity.createdAt],
                    updatedAt = it[CustomerEntity.updatedAt] ?: it[CustomerEntity.createdAt],
                    deletedAt = it[CustomerEntity.deletedAt]
                )
            }
    }

    override suspend fun findAll(): List<Customer> = dbQuery {
        CustomerEntity.selectAll()
            .map {
                Customer(
                    id = it[CustomerEntity.id],
                    name = it[CustomerEntity.name],
                    documentType = it[CustomerEntity.documentType],
                    document = it[CustomerEntity.document],
                    phone = it[CustomerEntity.phone],
                    email = it[CustomerEntity.email],
                    createdAt = it[CustomerEntity.createdAt],
                    updatedAt = it[CustomerEntity.updatedAt] ?: it[CustomerEntity.createdAt],
                    deletedAt = it[CustomerEntity.deletedAt]
                )
            }
    }

    override suspend fun findActiveCustomers(): List<Customer> = dbQuery {
        CustomerEntity.selectAll()
            .where { CustomerEntity.deletedAt.isNull() }
            .map {
                Customer(
                    id = it[CustomerEntity.id],
                    name = it[CustomerEntity.name],
                    documentType = it[CustomerEntity.documentType],
                    document = it[CustomerEntity.document],
                    phone = it[CustomerEntity.phone],
                    email = it[CustomerEntity.email],
                    createdAt = it[CustomerEntity.createdAt],
                    updatedAt = it[CustomerEntity.updatedAt] ?: it[CustomerEntity.createdAt],
                    deletedAt = it[CustomerEntity.deletedAt]
                )
            }
    }

    override suspend fun update(id: Int, customer: Customer): Boolean = dbQuery {
        CustomerEntity.update({ CustomerEntity.id eq id }) {
            it[name] = customer.name
            it[documentType] = customer.documentType
            it[document] = customer.document
            it[phone] = customer.phone
            it[email] = customer.email
            it[updatedAt] = LocalDateTime.now()
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        CustomerEntity.deleteWhere { CustomerEntity.id eq id } > 0
    }

    override suspend fun softDelete(id: Int): Boolean = dbQuery {
        CustomerEntity.update({ CustomerEntity.id eq id }) {
            it[deletedAt] = LocalDateTime.now()
            it[updatedAt] = LocalDateTime.now()
        } > 0
    }

    override suspend fun restore(id: Int): Boolean = dbQuery {
        CustomerEntity.update({ CustomerEntity.id eq id }) {
            it[deletedAt] = null
            it[updatedAt] = LocalDateTime.now()
        } > 0
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}