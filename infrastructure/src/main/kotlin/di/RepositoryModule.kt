package di

import org.koin.dsl.module
import persistence.repository.CustomerRepositoryImpl
import persistence.repository.UserRepositoryImpl
import repository.CustomerRepository
import repository.UserRepository

object RepositoryModule {
    val module = module {
        single<UserRepository> { UserRepositoryImpl() }
        single<CustomerRepository> { CustomerRepositoryImpl() }
    }
}