package rmb.presentation.mappers

import rmb.domain.entities.AddressEntity
import rmb.presentation.dto.address.AddressResponse

fun AddressEntity.toResponse() =
    AddressResponse(
        id = id ?: 0,
        city = city,
        street = street,
        houseNumber = houseNumber,
        subHouseNumber = subHouseNumber,
        postalCode = postalCode,
    )
