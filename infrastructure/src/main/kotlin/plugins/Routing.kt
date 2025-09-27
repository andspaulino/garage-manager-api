package plugins

import application.usecase.FindAllUsersUseCase
import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject
import routes.userRoutes

fun Application.configureRouting() {

    val findAllUsersUseCase by inject<FindAllUsersUseCase>()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        
        userRoutes(
            findAllUsersUseCase = findAllUsersUseCase
        )
    }
}