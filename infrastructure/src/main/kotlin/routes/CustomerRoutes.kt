package routes

import application.dto.CreateCustomerRequest
import application.usecase.CreateCustomerUseCase
import application.usecase.FindCustomerUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.customerRoutes(
    createCustomerUseCase: CreateCustomerUseCase,
    findCustomerUseCase: FindCustomerUseCase
) {
    route("/customers") {
        create(createCustomerUseCase)
    }
}

private fun Route.create(createCustomerUseCase: CreateCustomerUseCase) {
    post {
        val request = call.receive<CreateCustomerRequest>()

        createCustomerUseCase(request).fold(
            onSuccess = { customerResponse ->
                call.respond(HttpStatusCode.Created, customerResponse)
            },
            onFailure = { throwable ->
                val status = when (throwable) {
                    is IllegalStateException -> HttpStatusCode.BadRequest
                    else -> HttpStatusCode.InternalServerError
                }
                call.respond(status, mapOf("error" to (throwable.message ?: "Unknown error")))
            }
        )
    }
}

//privatee fun Route.findById(findCustomerUseCase: FindCustomerUseCase) {
//    get {
//        try {
//            val users = findAllUsersUseCase
//            call.respond(HttpStatusCode.OK, users)
//        } catch (e: Exception) {
//            call.respond(
//                HttpStatusCode.InternalServerError,
//                mapOf("error" to "Failed to fetch users: ${e.message}")
//            )
//        }
//    }
//}