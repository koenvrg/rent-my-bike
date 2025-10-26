package rmb.application.usecases.advertisement

import rmb.application.exceptions.AdvertisementNotFoundException
import rmb.domain.entities.AddressEntity
import rmb.domain.entities.AdvertisementEntity
import rmb.domain.repositories.AdvertisementRepositoryInterface
import rmb.presentation.dto.advertisement.UpdateAdvertisement

class UpdateAdvertisementUsecase(
    private val advertisementRepository: AdvertisementRepositoryInterface,
) {
    operator fun invoke(advertisementRequest: UpdateAdvertisement): AdvertisementEntity {
        val advId = advertisementRequest.bikeId
        val foundAdvertisement =
            advertisementRepository.findOneBybikeId(advId)
                ?: throw AdvertisementNotFoundException("Advertisement with id $advId not found")

        return advertisementRepository.save(createAdvertisementEntity(advertisementRequest, foundAdvertisement))
    }

    private fun createAdvertisementEntity(
        request: UpdateAdvertisement,
        foundAdvertisement: AdvertisementEntity,
    ) = AdvertisementEntity(
        bikeId = foundAdvertisement.bikeId,
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
