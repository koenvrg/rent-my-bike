package rmb.application.usecases.rental

import rmb.application.exceptions.RentalNotFoundException
import rmb.domain.entities.RentalEntity
import rmb.domain.repositories.RentalRepositoryInterface

class GetRentalUsecase(
    private val rentalRepository: RentalRepositoryInterface,
) {
    operator fun invoke(rentalId: Int): RentalEntity {
        val rental =
            rentalRepository.findById(rentalId)
                ?: throw RentalNotFoundException("Rental with id $rentalId not found")

        requireNotNull(rental.id) { "Cannot get rental without ID" }

        return rental
    }
}
