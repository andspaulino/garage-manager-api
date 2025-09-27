package di

import application.usecase.CreateCustomerUseCase
import application.usecase.FindAllUsersUseCase
import application.usecase.FindCustomerUseCase
import org.koin.dsl.module

object UseCasesModule {
    val module = module {
        single<FindAllUsersUseCase> { FindAllUsersUseCase(get()) }
        single<CreateCustomerUseCase> { CreateCustomerUseCase(get()) }
        single<FindCustomerUseCase> { FindCustomerUseCase(get()) }
    }
}