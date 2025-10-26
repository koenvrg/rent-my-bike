package rmb.application.usecases.bike

import rmb.application.exceptions.UserNotFoundException
import rmb.domain.entities.bikeEntity
import rmb.domain.entities.bikeImageEntity
import rmb.domain.repositories.bikeImageRepositoryInterface
import rmb.domain.repositories.bikeRepositoryInterface
import rmb.domain.repositories.UserRepositoryInterface
import rmb.domain.validatie.ExistingbikeValidator
import rmb.presentation.dto.bike.Createbike
import rmb.presentation.dto.image.CreatebikeImage
import kotlin.collections.mapIndexed

class CreatebikeUsecase(
    private val bikeRepository: bikeRepositoryInterface,
    private val userRepository: UserRepositoryInterface,
    private val existingbikeValidator: ExistingbikeValidator,
    private val bikeImageRepository: bikeImageRepositoryInterface,
) {
    operator fun invoke(
        bikeRequest: Createbike,
        userId: Int,
    ): bikeEntity {
        val user =
            userRepository.findById(userId)
                ?: throw UserNotFoundException("User with id $userId not found")

        user.ensureCustomer()

        existingbikeValidator(bikeRequest.licensePlate)

        val bikeEntity = createbikeEntity(bikeRequest, userId)

        return savebikeWithImages(bikeRepository.save(bikeEntity), bikeRequest.bikeImages)
    }

    private fun createbikeEntity(
        request: Createbike,
        userId: Int,
    ): bikeEntity =
        bikeEntity(
            fuelType = request.fuelType,
            userId = userId,
            bodyType = request.bodyType,
            brand = request.brand,
            model = request.model,
            modelYear = request.modelYear,
            licensePlate = request.licensePlate,
            mileage = request.mileage,
            createdStamp = request.createdStamp,
        )

    private fun savebikeWithImages(
        bike: bikeEntity,
        imagesRequest: List<CreatebikeImage>,
    ): bikeEntity {
        requireNotNull(bike.id) { "Cannot create bike images without bike ID" }

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
