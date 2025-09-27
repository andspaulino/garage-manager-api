package routes

import application.usecase.FindAllUsersUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.userRoutes(
    findAllUsersUseCase: FindAllUsersUseCase
) {
    route("/users") {
        findAll(findAllUsersUseCase)
    }
}

private fun Route.findAll(findAllUsersUseCase: FindAllUsersUseCase) {
    get {
        try {
            val users = findAllUsersUseCase()
            call.respond(HttpStatusCode.OK, users)
        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.InternalServerError,
                mapOf("error" to "Failed to fetch users: ${e.message}")
            )
        }
    }
}