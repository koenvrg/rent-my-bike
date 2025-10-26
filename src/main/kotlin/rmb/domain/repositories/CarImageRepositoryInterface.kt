package rmb.domain.repositories

import rmb.domain.entities.bikeEntity
import rmb.domain.entities.bikeImageEntity

interface bikeImageRepositoryInterface {
    fun findBybikeId(bikeId: Int): List<bikeImageEntity>

    fun deleteAllBybike(bike: bikeEntity): Boolean

    fun save(bikeImage: bikeImageEntity): bikeImageEntity
}
