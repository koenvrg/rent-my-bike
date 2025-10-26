package rmb.presentation.dto.advertisement

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import rmb.presentation.dto.address.CreateAddress

@Serializable
data class CreateAdvertisement(
    val bikeId: Int,
    val address: CreateAddress,
    val availableFrom: LocalDateTime,
    val availableUntil: LocalDateTime,
    val price: Double,
)
