package rmb.application.usecases.bike

import rmb.domain.entities.bikeEntity
import rmb.domain.repositories.bikeImageRepositoryInterface
import rmb.domain.repositories.bikeRepositoryInterface

class GetPersonalbikesUsecase(
    private val bikeRepository: bikeRepositoryInterface,
    private val bikeImageRepository: bikeImageRepositoryInterface,
) {
    operator fun invoke(userId: Int): List<bikeEntity> {
        val bikes = bikeRepository.getAllbikesByUserId(userId)

        for (bike in bikes) {
            requireNotNull(bike.id) { "Cannot get bike without ID" }

            val images = bikeImageRepository.findBybikeId(bike.id)

            bike.setImages(images)
        }

        return bikes
    }
}
