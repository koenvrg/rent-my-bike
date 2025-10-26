package rmb.infrastructure.mappers

import org.jetbrains.exposed.sql.ResultRow
import rmb.domain.entities.UserEntity
import rmb.domain.repositories.AddressRepositoryInterface
import rmb.infrastructure.tables.UserTable

fun ResultRow.toUserEntity(addressRepository: AddressRepositoryInterface): UserEntity {
    val addressId = this[UserTable.addressId]
    val address = addressId.let { addressRepository.findById(it)!! }

    return UserEntity(
        id = this[UserTable.id],
        userType = this[UserTable.userType],
        address = address,
        email = this[UserTable.email],
        password = this[UserTable.password],
        firstName = this[UserTable.firstName],
        lastName = this[UserTable.lastName],
        phone = this[UserTable.phone],
        userPoints = this[UserTable.userPoints],
    )
}
