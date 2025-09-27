package repository

import model.Customer

interface CustomerRepository {
    suspend fun create(customer: Customer): Int
    suspend fun findById(id: Int): Customer?
    suspend fun findByDocument(document: String): Customer?
    suspend fun findAll(): List<Customer>
    suspend fun findActiveCustomers(): List<Customer>
    suspend fun update(id: Int, customer: Customer): Boolean
    suspend fun delete(id: Int): Boolean
    suspend fun softDelete(id: Int): Boolean
    suspend fun restore(id: Int): Boolean
}