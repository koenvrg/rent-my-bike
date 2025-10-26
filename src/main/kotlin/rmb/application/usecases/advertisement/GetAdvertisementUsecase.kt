package rmb.application.usecases.advertisement

import rmb.application.exceptions.AdvertisementNotFoundException
import rmb.domain.entities.AdvertisementEntity
import rmb.domain.repositories.AdvertisementRepositoryInterface

class GetAdvertisementUsecase(
    private val advertisementRepository: AdvertisementRepositoryInterface,
) {
    operator fun invoke(advertisementId: Int): AdvertisementEntity =
        advertisementRepository.findById(advertisementId)
            ?: throw AdvertisementNotFoundException("Advertisement with id $advertisementId not found")
}
