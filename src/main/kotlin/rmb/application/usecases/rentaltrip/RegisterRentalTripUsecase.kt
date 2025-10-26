package rmb.application.usecases.rentaltrip

import rmb.application.exceptions.RentalNotFoundException
import rmb.domain.entities.RentalTripEntity
import rmb.domain.repositories.RentalRepositoryInterface
import rmb.domain.repositories.RentalTripRepositoryInterface
import rmb.presentation.dto.rentaltrip.RegisterRentalTrip

class RegisterRentalTripUsecase(
    private val rentalTripRepository: RentalTripRepositoryInterface,
    private val rentalRepository: RentalRepositoryInterface,
) {
    operator fun invoke(request: RegisterRentalTrip): RentalTripEntity {
        val rentalId = request.rentalId
        if (rentalRepository.findById(rentalId) == null) {
            throw RentalNotFoundException("Rental with id $rentalId not found")
        }

        return rentalTripRepository.save(createRentalTripEntity(request))
    }

    private fun createRentalTripEntity(request: RegisterRentalTrip) =
        RentalTripEntity(
            rentalId = request.rentalId,
            startMileage = request.startMileage,
            endMileage = request.endMileage,
            startDate = request.startDate,
            endDate = request.endDate,
        )
}
