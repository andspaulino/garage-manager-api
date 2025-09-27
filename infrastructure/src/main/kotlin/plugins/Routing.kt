package plugins

import application.usecase.CreateCustomerUseCase
import application.usecase.FindAllUsersUseCase
import application.usecase.FindCustomerByIdUseCase
import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject
import routes.customerRoutes
import routes.userRoutes

fun Application.configureRouting() {

    val findAllUsersUseCase by inject<FindAllUsersUseCase>()
    val createCustomerUseCase by inject<CreateCustomerUseCase>()
    val findCustomerByIdUseCase by inject<FindCustomerByIdUseCase>()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        
        userRoutes(
            findAllUsersUseCase = findAllUsersUseCase
        )

        customerRoutes(
            createCustomerUseCase = createCustomerUseCase,
            findCustomerByIdUseCase = findCustomerByIdUseCase
        )
    }
}