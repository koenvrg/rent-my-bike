package rmb.presentation.dto.advertisement

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import rmb.presentation.dto.address.AddressResponse

@Serializable
data class AdvertisementResponse(
    val id: Int? = null,
    val bikeId: Int,
    val address: AddressResponse,
    val availableFrom: LocalDateTime,
    val availableUntil: LocalDateTime,
    val price: Double,
)
