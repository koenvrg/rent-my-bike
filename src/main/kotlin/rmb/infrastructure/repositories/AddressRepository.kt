package rmb.infrastructure.repositories

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import rmb.domain.entities.AddressEntity
import rmb.domain.repositories.AddressRepositoryInterface
import rmb.infrastructure.mappers.toAddressEntity
import rmb.infrastructure.tables.AddressTable

class AddressRepository : AddressRepositoryInterface {
    override fun findById(id: Int): AddressEntity? =
        transaction {
            AddressTable.selectAll().where { AddressTable.id eq id }
                .map { it.toAddressEntity() }
                .singleOrNull()
        }

    override fun save(address: AddressEntity): AddressEntity =
        transaction {
            if (address.id == null) {
                val id =
                    AddressTable.insert {
                        it[city] = address.city
                        it[street] = address.street
                        it[houseNumber] = address.houseNumber
                        it[subHouseNumber] = address.subHouseNumber
                        it[postalCode] = address.postalCode
                    } get AddressTable.id

                return@transaction address.copy(id = id)
            }

            AddressTable.update({ AddressTable.id eq address.id }) {
                it[city] = address.city
                it[street] = address.street
                it[houseNumber] = address.houseNumber
                it[subHouseNumber] = address.subHouseNumber
                it[postalCode] = address.postalCode
            }

            address
        }
}
