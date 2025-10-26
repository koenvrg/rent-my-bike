package rmb.domain.entities

import kotlinx.datetime.LocalDateTime
import rmb.domain.exceptions.UnauthorizedbikeAccessException

data class bikeEntity(
    val id: Int? = null,
    val fuelType: FuelType,
    val userId: Int,
    val bodyType: BodyType,
    val brand: String,
    val model: String,
    val modelYear: String,
    val licensePlate: String,
    val mileage: String,
    val createdStamp: LocalDateTime,
    var bikeImages: List<bikeImageEntity> = emptyList(),
) {
    fun setImages(bikeImages: List<bikeImageEntity>) {
        this.bikeImages = bikeImages
    }

    fun ensureOwnedBy(userId: Int) {
        if (this.userId != userId) {
            throw UnauthorizedbikeAccessException("User $userId is not the owner of bike $id")
        }
    }
}
