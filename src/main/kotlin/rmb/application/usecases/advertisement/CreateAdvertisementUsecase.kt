package rmb.application.usecases.advertisement

import rmb.application.exceptions.bikeNotFoundException
import rmb.domain.entities.AddressEntity
import rmb.domain.entities.AdvertisementEntity
import rmb.domain.repositories.AdvertisementRepositoryInterface
import rmb.domain.repositories.bikeRepositoryInterface
import rmb.domain.validatie.ExistingbikeAdvertisementValidator
import rmb.presentation.dto.advertisement.CreateAdvertisement

class CreateAdvertisementUsecase(
    private val bikeRepository: bikeRepositoryInterface,
    private val advertisementRepository: AdvertisementRepositoryInterface,
    private val existingbikeAdvertisementValidator: ExistingbikeAdvertisementValidator,
) {
    operator fun invoke(
        advertisementRequest: CreateAdvertisement,
        userId: Int,
    ): AdvertisementEntity {
        val bike =
            bikeRepository.findById(advertisementRequest.bikeId)
                ?: throw bikeNotFoundException("bike with id ${advertisementRequest.bikeId} not found")

        requireNotNull(bike.id) { "Cannot create advertisement without bike ID" }

        bike.ensureOwnedBy(userId)

        existingbikeAdvertisementValidator(bike.id)

        return advertisementRepository.save(createAdvertisementEntity(advertisementRequest))
    }

    private fun createAdvertisementEntity(request: CreateAdvertisement) =
        AdvertisementEntity(
            bikeId = request.bikeId,
            address =
                with(request.address) {
                    AddressEntity(
                        city = city,
                        street = street,
                        houseNumber = houseNumber,
                        subHouseNumber = subHouseNumber,
                        postalCode = postalCode,
                    )
                },
            availableFrom = request.availableFrom,
            availableUntil = request.availableUntil,
            price = request.price,
        )
}
