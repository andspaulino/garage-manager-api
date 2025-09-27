package routes

import application.usecase.FindAllUsersUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Routing.userRoutes(
    findAllUsersUseCase: FindAllUsersUseCase,
) {
    route("/users") {
        
        get {
            try {
                val users = findAllUsersUseCase.execute()
                call.respond(HttpStatusCode.OK, users)
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError, 
                    mapOf("error" to "Failed to fetch users: ${e.message}")
                )
            }
        }
    }
}