package rmb.infrastructure.mappers

import org.jetbrains.exposed.sql.ResultRow
import rmb.domain.entities.AdvertisementEntity
import rmb.domain.repositories.AddressRepositoryInterface
import rmb.infrastructure.tables.AdvertisementTable

fun ResultRow.toAdvertisementEntity(addressRepository: AddressRepositoryInterface): AdvertisementEntity {
    val addressId = this[AdvertisementTable.addressId]
    val address = addressId.let { addressRepository.findById(it)!! }

    return AdvertisementEntity(
        id = this[AdvertisementTable.id],
        bikeId = this[AdvertisementTable.bikeId],
        address = address,
        availableFrom = this[AdvertisementTable.availableFrom],
        availableUntil = this[AdvertisementTable.availableUntil],
        price = this[AdvertisementTable.price],
    )
}
