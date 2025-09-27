package application.usecase

import application.dto.CreateCustomerRequest
import application.dto.CustomerResponse
import application.mapper.toCustomerResponse
import application.mapper.toDomain
import repository.CustomerRepository

class CreateCustomerUseCase(
    private val customerRepository: CustomerRepository
) {
    suspend operator fun invoke(req: CreateCustomerRequest): Result<CustomerResponse> {
        return runCatching {
            customerRepository.findByDocument(req.document)?.let {
                throw IllegalStateException("customer with document already exists")
            }

            val customer = req.toDomain()

            val id = customerRepository.create(customer)
            val created = customer.copy(id = id)

            created.toCustomerResponse()
        }
    }
}