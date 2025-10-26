package rmb.presentation.mappers

import rmb.domain.entities.RentalTripEntity
import rmb.presentation.dto.rentaltrip.RentalTripResponse

fun RentalTripEntity.toResponse() =
    RentalTripResponse(
        id = id,
        rentalId = rentalId,
        startMileage = startMileage,
        endMileage = endMileage,
        startDate = startDate,
        endDate = endDate,
    )
