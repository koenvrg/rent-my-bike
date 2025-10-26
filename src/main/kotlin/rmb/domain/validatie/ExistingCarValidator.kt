package rmb.domain.validatie

import rmb.domain.exceptions.bikeAlreadyExistsException
import rmb.domain.repositories.bikeRepositoryInterface

class ExistingbikeValidator(private val bikeRepository: bikeRepositoryInterface) {
    operator fun invoke(licensePlate: String) {
        if (bikeRepository.findByLicensePlate(licensePlate) != null) {
            throw bikeAlreadyExistsException("bike with this license plate already exists")
        }
    }
}
