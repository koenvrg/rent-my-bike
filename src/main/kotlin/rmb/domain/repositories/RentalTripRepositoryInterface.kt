package rmb.domain.repositories

import rmb.domain.entities.RentalTripEntity

interface RentalTripRepositoryInterface {
    fun save(rentalTrip: RentalTripEntity): RentalTripEntity

    fun findByRentalId(rentalId: Int): List<RentalTripEntity>
}
