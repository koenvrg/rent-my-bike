package rmb.application.usecases.advertisement

import rmb.application.exceptions.AdvertisementNotFoundException
import rmb.application.exceptions.bikeNotFoundException
import rmb.domain.repositories.AdvertisementRepositoryInterface
import rmb.domain.repositories.bikeRepositoryInterface
import rmb.domain.repositories.RentalRepositoryInterface

class DeleteAdvertisementUsecase(
    private val advertisementRepository: AdvertisementRepositoryInterface,
    private val bikeRepository: bikeRepositoryInterface,
    private val rentalRepository: RentalRepositoryInterface,
) {
    operator fun invoke(
        advertisementId: Int,
        userId: Int,
    ) {
        val advertisement =
            advertisementRepository.findById(advertisementId)
                ?: throw AdvertisementNotFoundException("Advertisement with id $advertisementId not found")

        requireNotNull(advertisement.id) { "Cannot delete advertisement without ID" }

        val bike =
            bikeRepository.findById(advertisement.bikeId)
                ?: throw bikeNotFoundException("Cannot find bike with id ${advertisement.bikeId}")

        bike.ensureOwnedBy(userId)

        val rentals = rentalRepository.findAllByAdvertisementId(advertisement.id)

        advertisement.canBeDeleted(rentals)

        advertisementRepository.delete(advertisement.id)
    }
}
