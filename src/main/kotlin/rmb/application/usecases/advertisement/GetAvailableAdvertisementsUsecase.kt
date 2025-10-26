package rmb.application.usecases.advertisement

import rmb.domain.entities.AdvertisementEntity
import rmb.domain.repositories.AdvertisementRepositoryInterface

class GetAvailableAdvertisementsUsecase(
    private val advertisementRepository: AdvertisementRepositoryInterface,
) {
    operator fun invoke(): List<AdvertisementEntity> = advertisementRepository.getAllAvailable()
}
