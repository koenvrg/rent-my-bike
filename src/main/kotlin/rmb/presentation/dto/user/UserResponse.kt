package rmb.presentation.dto.user

import kotlinx.serialization.Serializable
import rmb.domain.entities.UserType
import rmb.presentation.dto.address.AddressResponse

@Serializable
data class UserResponse(
    val id: Int,
    val userType: UserType,
    val address: AddressResponse,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val userPoints: Int,
)
