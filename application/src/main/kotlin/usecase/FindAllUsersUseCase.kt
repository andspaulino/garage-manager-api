package application.usecase

import application.dto.UserResponseDto
import repository.UserRepository
import java.time.format.DateTimeFormatter

class FindAllUsersUseCase(private val userRepository: UserRepository) {
    
    suspend fun execute(): List<UserResponseDto> {
        val users = userRepository.findAll()
        
        return users.map { user ->
            UserResponseDto(
                id = user.id!!,
                username = user.username,
                role = user.role.name,
                isActive = user.isActive,
                createdAt = user.createdAt?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            )
        }
    }
}