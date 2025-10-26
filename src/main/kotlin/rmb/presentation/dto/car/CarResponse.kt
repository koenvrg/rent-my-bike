package rmb.presentation.dto.bike

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import rmb.domain.entities.BodyType
import rmb.domain.entities.FuelType
import rmb.presentation.dto.image.bikeImageResponse

@Serializable
data class bikeResponse(
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
    val bikeImages: List<bikeImageResponse> = emptyList(),
)
