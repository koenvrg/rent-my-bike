package rmb.presentation.mappers

import rmb.domain.entities.bikeImageEntity
import rmb.presentation.dto.image.bikeImageResponse

fun bikeImageEntity.toResponse() =
    bikeImageResponse(
        id = id,
        bikeId = bikeId,
        image = image,
        weight = weight,
    )
