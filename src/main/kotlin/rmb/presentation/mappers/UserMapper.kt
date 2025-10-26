package rmb.presentation.mappers

import rmb.domain.entities.UserEntity
import rmb.presentation.dto.user.UserResponse

fun UserEntity.toResponse() =
    UserResponse(
        id = id ?: 0,
        userType = userType,
        address = address.toResponse(),
        email = email,
        password = password,
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        userPoints = userPoints,
    )
