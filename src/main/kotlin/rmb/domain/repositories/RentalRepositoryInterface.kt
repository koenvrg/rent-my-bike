package rmb.domain.repositories

import rmb.domain.entities.RentalEntity

interface RentalRepositoryInterface {
    fun findById(id: Int): RentalEntity?

    fun findAllByAdvertisementId(advertisementId: Int): List<RentalEntity>

    fun save(rental: RentalEntity): RentalEntity
}
