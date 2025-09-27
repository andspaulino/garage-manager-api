package di

import application.usecase.FindAllUsersUseCase
import org.koin.dsl.module

object UseCasesModule {
    val module = module {
        single<FindAllUsersUseCase> { FindAllUsersUseCase(get()) }
    }
}