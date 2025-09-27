package application.usecase

import repository.CustomerRepository

class FindCustomerUseCase(
    private val customerRepository: CustomerRepository
) {
}