package rmb.application.usecases.bike

import rmb.application.exceptions.bikeNotFoundException
import rmb.application.exceptions.UserNotFoundException
import rmb.domain.entities.bikeEntity
import rmb.domain.entities.bikeImageEntity
import rmb.domain.repositories.bikeImageRepositoryInterface
import rmb.domain.repositories.bikeRepositoryInterface
import rmb.domain.repositories.UserRepositoryInterface
import rmb.domain.validatie.ExistingbikeValidator
import rmb.presentation.dto.bike.Updatebike
import rmb.presentation.dto.image.CreatebikeImage

class UpdatebikeUsecase(
    private val bikeRepository: bikeRepositoryInterface,
    private val existingbikeValidator: ExistingbikeValidator,
    private val userRepository: UserRepositoryInterface,
    private val bikeImageRepository: bikeImageRepositoryInterface,
) {
    operator fun invoke(
        bikeRequest: Updatebike,
        userId: Int,
    ): bikeEntity {
        val user =
            userRepository.findById(userId)
                ?: throw UserNotFoundException("User with id $userId not found")

        user.ensureCustomer()

        val existingbike =
            bikeRepository.findById(bikeRequest.id!!)
                ?: throw bikeNotFoundException("bike with id ${bikeRequest.id} not found")

        existingbike.ensureOwnedBy(userId)
        if (existingbike.licensePlate != bikeRequest.licensePlate) {
            existingbikeValidator(bikeRequest.licensePlate)
        }

        val bikeEntity = createbikeEntity(existingbike, bikeRequest)

        val bike = bikeRepository.save(bikeEntity)

        return savebikeWithImages(bike, bikeRequest.bikeImages)
    }

    private fun createbikeEntity(
        bike: bikeEntity,
        request: Updatebike,
    ): bikeEntity =
        bike.copy(
            fuelType = request.fuelType,
            bodyType = request.bodyType,
            brand = request.brand,
            model = request.model,
            modelYear = request.modelYear,
            licensePlate = request.licensePlate,
            mileage = request.mileage,
        )

    private fun savebikeWithImages(
        bike: bikeEntity,
        imagesRequest: List<CreatebikeImage>,
    ): bikeEntity {
        requireNotNull(bike.id) { "Cannot create bike images without bike ID" }

        bikeImageRepository.deleteAllBybike(bike)

        val images =
            imagesRequest.mapIndexed { index, imageRequest ->
                bikeImageRepository.save(
                    bikeImageEntity(
                        image = imageRequest.image,
                        weight = index + 1,
                        bikeId = bike.id,
                    ),
                )
            }

        bike.setImages(images)

        return bike
    }
}
