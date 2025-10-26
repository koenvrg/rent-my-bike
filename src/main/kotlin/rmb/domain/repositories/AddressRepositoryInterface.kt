package rmb.domain.repositories

import rmb.domain.entities.AddressEntity

interface AddressRepositoryInterface {
    fun findById(id: Int): AddressEntity?

    fun save(address: AddressEntity): AddressEntity
}
