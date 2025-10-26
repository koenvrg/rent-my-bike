package rmb.presentation.dto.rental

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import rmb.domain.entities.RentalStatus

@Serializable
data class RentalResponse(
    val id: Int? = null,
    val userId: Int,
    val advertisementId: Int,
    val rentalStatus: RentalStatus,
    val pickUpDate: LocalDateTime,
    val returningDate: LocalDateTime,
)
