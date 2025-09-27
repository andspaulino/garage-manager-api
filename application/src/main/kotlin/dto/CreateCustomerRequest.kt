package application.dto

import enums.DocumentType
import kotlinx.serialization.Serializable

@Serializable
data class CreateCustomerRequest(
    val name: String,
    val documentType: DocumentType,
    val document: String,
    val phone: String? = null,
    val email: String? = null
) {
    init {
        require(name.isNotBlank()) { "name is required" }
        require(document.isNotBlank()) { "document is required" }
    }
}