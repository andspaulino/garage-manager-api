package application.usecase

import application.dto.UserResponse
import repository.UserRepository
import java.time.format.DateTimeFormatter

class FindAllUsersUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): List<UserResponse> {
        val users = userRepository.findAll()
        
        return users.map { user ->
            UserResponse(
                id = user.id!!,
                username = user.username,
                role = user.role.name,
                isActive = user.isActive,
                createdAt = user.createdAt?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            )
        }
    }
}