package rmb.presentation.mappers

import rmb.domain.entities.bikeEntity
import rmb.presentation.dto.bike.bikeResponse

fun bikeEntity.toResponse() =
    bikeResponse(
        id = id,
        fuelType = fuelType,
        userId = userId,
        bodyType = bodyType,
        brand = brand,
        model = model,
        modelYear = modelYear,
        licensePlate = licensePlate,
        mileage = mileage,
        createdStamp = createdStamp,
        bikeImages = bikeImages.map { it.toResponse() },
    )
