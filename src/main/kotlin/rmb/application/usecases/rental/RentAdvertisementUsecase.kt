package rmb.application.usecases.rental

import rmb.application.exceptions.AdvertisementNotFoundException
import rmb.application.exceptions.UserNotFoundException
import rmb.domain.entities.RentalEntity
import rmb.domain.repositories.AdvertisementRepositoryInterface
import rmb.domain.repositories.RentalRepositoryInterface
import rmb.domain.repositories.UserRepositoryInterface
import rmb.presentation.dto.rental.RentAdvertisement

class RentAdvertisementUsecase(
    private val rentalRepository: RentalRepositoryInterface,
    private val userRepository: UserRepositoryInterface,
    private val advertisementRepository: AdvertisementRepositoryInterface,
) {
    operator fun invoke(
        request: RentAdvertisement,
        userId: Int,
    ): RentalEntity {
        userRepository.findById(userId)
            ?: throw UserNotFoundException("User with id $userId not found")

        val advertisementId = request.advertisementId
        if (advertisementRepository.findById(advertisementId) == null) {
            throw AdvertisementNotFoundException("Advertisement with id $advertisementId not found")
        }

        return rentalRepository.save(createRentalEntity(request, userId))
    }

    private fun createRentalEntity(
        request: RentAdvertisement,
        userId: Int,
    ) = RentalEntity(
        userId = userId,
        advertisementId = request.advertisementId,
        rentalStatus = request.rentalStatus,
        pickUpDate = request.pickUpDate,
        returningDate = request.returningDate,
    )
}
