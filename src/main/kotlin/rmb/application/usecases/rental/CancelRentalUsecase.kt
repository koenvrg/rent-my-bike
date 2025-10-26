package rmb.application.usecases.rental

import rmb.application.exceptions.RentalNotFoundException
import rmb.domain.entities.RentalEntity
import rmb.domain.entities.RentalStatus
import rmb.domain.repositories.RentalRepositoryInterface

class CancelRentalUsecase(
    private val rentalRepository: RentalRepositoryInterface,
) {
    operator fun invoke(rentalId: Int): RentalEntity {
        val rental =
            rentalRepository.findById(rentalId)
                ?: throw RentalNotFoundException("Rental with id $rentalId not found")

        rental.ensureStatusNotActiveOrCompleted()

        return rentalRepository.save(rental.copy(rentalStatus = RentalStatus.CANCELLED))
    }
}
