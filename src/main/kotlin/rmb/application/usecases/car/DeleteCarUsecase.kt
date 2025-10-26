package rmb.application.usecases.bike

import rmb.application.exceptions.bikeNotFoundException
import rmb.domain.repositories.AdvertisementRepositoryInterface
import rmb.domain.repositories.bikeImageRepositoryInterface
import rmb.domain.repositories.bikeRepositoryInterface
import rmb.domain.repositories.RentalRepositoryInterface

class DeletebikeUsecase(
    private val bikeRepository: bikeRepositoryInterface,
    private val bikeImageRepository: bikeImageRepositoryInterface,
    private val advertisementRepository: AdvertisementRepositoryInterface,
    private val rentalRepository: RentalRepositoryInterface,
) {
    operator fun invoke(
        bikeId: Int,
        userId: Int,
    ) {
        val bike =
            bikeRepository.findById(bikeId)
                ?: throw bikeNotFoundException("bike with id $bikeId not found")

        bike.ensureOwnedBy(userId)

        val advertisement = advertisementRepository.findOneBybikeId(bikeId)

        if (advertisement != null) {
            requireNotNull(advertisement.id) { "Cannot delete advertisement without ID" }

            val rentals = rentalRepository.findAllByAdvertisementId(advertisement.id)

            advertisement.canBeDeleted(rentals)

            advertisementRepository.delete(advertisement.id)
        }

        bikeImageRepository.deleteAllBybike(bike)

        bikeRepository.delete(bikeId)
    }
}
