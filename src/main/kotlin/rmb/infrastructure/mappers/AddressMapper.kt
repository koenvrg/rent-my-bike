package rmb.infrastructure.mappers

import org.jetbrains.exposed.sql.ResultRow
import rmb.domain.entities.AddressEntity
import rmb.infrastructure.tables.AddressTable

fun ResultRow.toAddressEntity() =
    AddressEntity(
        id = this[AddressTable.id],
        city = this[AddressTable.city],
        street = this[AddressTable.street],
        houseNumber = this[AddressTable.houseNumber],
        subHouseNumber = this[AddressTable.subHouseNumber],
        postalCode = this[AddressTable.postalCode],
    )
