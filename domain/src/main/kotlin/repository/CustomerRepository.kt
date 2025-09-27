package repository

import model.Customer

interface CustomerRepository {
    suspend fun create(customer: Customer): Long
    suspend fun findById(id: Long): Customer?
    suspend fun findByDocument(document: String): Customer?
    suspend fun findAll(): List<Customer>
    suspend fun findActiveCustomers(): List<Customer>
    suspend fun update(id: Long, customer: Customer): Boolean
    suspend fun delete(id: Long): Boolean
    suspend fun softDelete(id: Long): Boolean
    suspend fun restore(id: Long): Boolean
}