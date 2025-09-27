package routes

import application.dto.CreateCustomerRequest
import application.usecase.CreateCustomerUseCase
import application.usecase.FindCustomerByIdUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.customerRoutes(
    createCustomerUseCase: CreateCustomerUseCase,
    findCustomerByIdUseCase: FindCustomerByIdUseCase
) {
    route("/customers") {
        create(createCustomerUseCase)
        findById(findCustomerByIdUseCase)
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

private fun Route.findById(findCustomerByIdUseCase: FindCustomerByIdUseCase) {
    get("{id}") {
        call.parameters["id"]?.let { id ->
            findCustomerByIdUseCase(id.toLong()).fold(
                onSuccess = { customerResponse ->
                    call.respond(HttpStatusCode.OK, customerResponse)
                },
                onFailure = { throwable ->
                    val status = when (throwable) {
                        is IllegalStateException -> HttpStatusCode.BadRequest
                        else -> HttpStatusCode.InternalServerError
                    }
                    call.respond(status, mapOf("error" to (throwable.message ?: "Unknown error")))
                }
            )
        } ?: call.respond(HttpStatusCode.BadRequest)
    }
}