package rmb.application.usecases.bike

import rmb.application.exceptions.bikeNotFoundException
import rmb.domain.entities.bikeEntity
import rmb.domain.repositories.bikeImageRepositoryInterface
import rmb.domain.repositories.bikeRepositoryInterface

class GetbikeUsecase(
    private val bikeRepository: bikeRepositoryInterface,
    private val bikeImageRepository: bikeImageRepositoryInterface,
) {
    operator fun invoke(bikeId: Int): bikeEntity {
        val bike =
            bikeRepository.findById(bikeId)
                ?: throw bikeNotFoundException("bike with id $bikeId not found")

        requireNotNull(bike.id) { "Cannot get bike without ID" }

        val images = bikeImageRepository.findBybikeId(bike.id)

        bike.setImages(images)

        return bike
    }
}
