package application.usecase

import application.dto.CustomerResponse
import application.mapper.toCustomerResponse
import repository.CustomerRepository

class FindCustomerByIdUseCase(
    private val customerRepository: CustomerRepository
) {
    suspend operator fun invoke(id: Long): Result<CustomerResponse> {
        return runCatching {
            customerRepository.findById(id)?.toCustomerResponse() ?: throw Exception("Customer not found")
        }
    }
}