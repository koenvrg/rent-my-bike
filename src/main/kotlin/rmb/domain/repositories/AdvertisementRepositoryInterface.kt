package rmb.domain.repositories

import rmb.domain.entities.AdvertisementEntity

interface AdvertisementRepositoryInterface {
    fun getAllAvailable(): List<AdvertisementEntity>

    fun findOneBybikeId(bikeId: Int): AdvertisementEntity?

    fun findById(id: Int): AdvertisementEntity?

    fun save(advertisement: AdvertisementEntity): AdvertisementEntity

    fun delete(id: Int): Boolean
}
