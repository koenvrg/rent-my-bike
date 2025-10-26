package rmb.infrastructure.repositories

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import rmb.domain.entities.UserEntity
import rmb.domain.repositories.AddressRepositoryInterface
import rmb.domain.repositories.UserRepositoryInterface
import rmb.infrastructure.mappers.toUserEntity
import rmb.infrastructure.tables.UserTable

class UserRepository(
    private val addressRepository: AddressRepositoryInterface,
) : UserRepositoryInterface {
    override fun findById(id: Int): UserEntity? =
        transaction {
            UserTable.selectAll().where { UserTable.id eq id }
                .map { it.toUserEntity(addressRepository) }.singleOrNull()
        }

    override fun findByEmail(email: String): UserEntity? =
        transaction {
            UserTable.selectAll().where { UserTable.email eq email }
                .map { it.toUserEntity(addressRepository) }
                .singleOrNull()
        }

    override fun save(user: UserEntity): UserEntity =
        transaction {
            val addressId = user.address.let { addressRepository.save(it).id!! }

            if (user.id == null) {
                val id =
                    UserTable.insert {
                        it[userType] = user.userType
                        it[UserTable.addressId] = addressId
                        it[email] = user.email
                        it[password] = user.password
                        it[firstName] = user.firstName
                        it[lastName] = user.lastName
                        it[phone] = user.phone
                        it[userPoints] = user.userPoints
                    } get UserTable.id

                return@transaction user.copy(id = id)
            }

            UserTable.update({ UserTable.id eq user.id }) {
                it[userType] = user.userType
                it[UserTable.addressId] = addressId
                it[email] = user.email
                it[password] = user.password
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[phone] = user.phone
                it[userPoints] = user.userPoints
            }

            user
        }
}
