package rmb.infrastructure.repositories

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import rmb.domain.entities.AdvertisementEntity
import rmb.domain.entities.RentalStatus
import rmb.domain.repositories.AddressRepositoryInterface
import rmb.domain.repositories.AdvertisementRepositoryInterface
import rmb.infrastructure.mappers.toAdvertisementEntity
import rmb.infrastructure.tables.AdvertisementTable
import rmb.infrastructure.tables.RentalTable

class AdvertisementRepository(
    private val addressRepository: AddressRepositoryInterface,
) : AdvertisementRepositoryInterface {
    override fun getAllAvailable(): List<AdvertisementEntity> =
        transaction {
            val activeRentalAds =
                RentalTable
                    .select(RentalTable.advertisementId)
                    .where { RentalTable.rentalStatus eq RentalStatus.ACTIVE }

            AdvertisementTable
                .selectAll().where { AdvertisementTable.id notInSubQuery activeRentalAds }
                .map { it.toAdvertisementEntity(addressRepository) }
        }

    override fun findOneBybikeId(bikeId: Int): AdvertisementEntity? =
        transaction {
            AdvertisementTable.selectAll().where { AdvertisementTable.bikeId eq bikeId }
                .map { it.toAdvertisementEntity(addressRepository) }.singleOrNull()
        }

    override fun findById(id: Int): AdvertisementEntity? =
        transaction {
            AdvertisementTable.selectAll().where { AdvertisementTable.id eq id }
                .map { it.toAdvertisementEntity(addressRepository) }.singleOrNull()
        }

    override fun save(advertisement: AdvertisementEntity): AdvertisementEntity =
        transaction {
            val addressId = advertisement.address.let { addressRepository.save(it).id!! }

            if (advertisement.id == null) {
                val id =
                    AdvertisementTable.insert {
                        it[bikeId] = advertisement.bikeId
                        it[AdvertisementTable.addressId] = addressId
                        it[availableFrom] = advertisement.availableFrom
                        it[availableUntil] = advertisement.availableUntil
                        it[price] = advertisement.price
                    } get AdvertisementTable.id

                return@transaction advertisement.copy(id = id)
            }

            AdvertisementTable.update({ AdvertisementTable.id eq advertisement.id }) {
                it[bikeId] = advertisement.bikeId
                it[AdvertisementTable.addressId] = addressId
                it[availableFrom] = advertisement.availableFrom
                it[availableUntil] = advertisement.availableUntil
                it[price] = advertisement.price
            }

            advertisement
        }

    override fun delete(id: Int): Boolean =
        transaction {
            AdvertisementTable.deleteWhere { AdvertisementTable.id eq id } > 0
        }
}
