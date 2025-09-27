package di

import org.koin.dsl.module
import persistence.repository.UserRepositoryImpl
import repository.UserRepository

object RepositoryModule {
    val module = module {
        single<UserRepository> { UserRepositoryImpl() }
    }
}