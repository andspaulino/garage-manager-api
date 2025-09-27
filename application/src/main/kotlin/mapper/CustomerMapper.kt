package application.mapper

import application.dto.CreateCustomerRequest
import application.dto.CustomerResponse
import model.Customer

fun CreateCustomerRequest.toDomain(): Customer =
    Customer(
        name = name,
        documentType = documentType,
        document = document,
        phone = phone,
        email = email
    )

fun Customer.toCustomerResponse(): CustomerResponse =
    CustomerResponse(
        id = id!!,
        name = name,
        documentType = documentType.code,
        document = document,
        phone = phone,
        email = email,
        createdAt = createdAt
    )