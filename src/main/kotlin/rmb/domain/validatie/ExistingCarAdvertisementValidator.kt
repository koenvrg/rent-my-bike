package rmb.domain.validatie

import rmb.domain.exceptions.AdvertisementAlreadyExistsForbikeException
import rmb.domain.repositories.AdvertisementRepositoryInterface

class ExistingbikeAdvertisementValidator(
    private val advertisementRepository: AdvertisementRepositoryInterface,
) {
    operator fun invoke(bikeId: Int) {
        if (advertisementRepository.findOneBybikeId(bikeId) != null) {
            throw AdvertisementAlreadyExistsForbikeException(
                "An advertisement for bike $bikeId already exists",
            )
        }
    }
}
