package rmb.presentation.mappers

import rmb.domain.entities.RentalEntity
import rmb.presentation.dto.rental.RentalResponse

fun RentalEntity.toResponse() =
    RentalResponse(
        id = id,
        userId = userId,
        advertisementId = advertisementId,
        rentalStatus = rentalStatus,
        pickUpDate = pickUpDate,
        returningDate = returningDate,
    )
