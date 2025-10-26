package rmb.domain.repositories

import rmb.domain.entities.UserEntity

interface UserRepositoryInterface {
    fun findById(id: Int): UserEntity?

    fun findByEmail(email: String): UserEntity?

    fun save(user: UserEntity): UserEntity
}
