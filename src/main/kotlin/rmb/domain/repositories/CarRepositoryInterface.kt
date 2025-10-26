package rmb.domain.repositories

import rmb.domain.entities.bikeEntity

interface bikeRepositoryInterface {
    fun getAllbikesByUserId(userId: Int): List<bikeEntity>

    fun findById(id: Int): bikeEntity?

    fun findByLicensePlate(licensePlate: String): bikeEntity?

    fun save(bike: bikeEntity): bikeEntity

    fun delete(id: Int): Boolean

    fun getAll(): List<bikeEntity>
}
