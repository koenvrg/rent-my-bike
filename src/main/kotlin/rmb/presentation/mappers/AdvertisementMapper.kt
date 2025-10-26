package rmb.presentation.mappers

import rmb.domain.entities.AdvertisementEntity
import rmb.presentation.dto.advertisement.AdvertisementResponse

fun AdvertisementEntity.toResponse() =
    AdvertisementResponse(
        id = id,
        bikeId = bikeId,
        address = address.toResponse(),
        availableFrom = availableFrom,
        availableUntil = availableUntil,
        price = price,
    )
