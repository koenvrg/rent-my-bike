package rmb.presentation.dto.user

import kotlinx.serialization.Serializable
import rmb.domain.entities.UserType
import rmb.presentation.dto.address.CreateAddress

@Serializable
data class CreateUser(
    val userType: UserType,
    val address: CreateAddress,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val userPoints: Int,
)
