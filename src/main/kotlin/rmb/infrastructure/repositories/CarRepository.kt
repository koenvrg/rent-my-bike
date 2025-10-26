package rmb.infrastructure.repositories

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import rmb.domain.entities.bikeEntity
import rmb.domain.repositories.bikeRepositoryInterface
import rmb.infrastructure.mappers.tobikeEntity
import rmb.infrastructure.tables.bikeTable

class bikeRepository() : bikeRepositoryInterface {
    override fun getAllbikesByUserId(userId: Int): List<bikeEntity> =
        transaction {
            bikeTable.selectAll().where { bikeTable.userId eq userId }
                .map { it.tobikeEntity() }
        }

    override fun findById(id: Int): bikeEntity? =
        transaction {
            bikeTable.selectAll().where { bikeTable.id eq id }
                .map { it.tobikeEntity() }
                .singleOrNull()
        }

    override fun findByLicensePlate(licensePlate: String): bikeEntity? =
        transaction {
            bikeTable.selectAll().where { bikeTable.licensePlate eq licensePlate }
                .map { it.tobikeEntity() }
                .singleOrNull()
        }

    override fun save(bike: bikeEntity): bikeEntity =
        transaction {
            if (bike.id == null) {
                val id =
                    bikeTable.insert {
                        it[fuelType] = bike.fuelType
                        it[bikeTable.userId] = bike.userId
                        it[bodyType] = bike.bodyType
                        it[brand] = bike.brand
                        it[model] = bike.model
                        it[modelYear] = bike.modelYear
                        it[licensePlate] = bike.licensePlate
                        it[mileage] = bike.mileage
                        it[createdStamp] = bike.createdStamp
                    } get bikeTable.id

                return@transaction bike.copy(id = id)
            }

            bikeTable.update({ bikeTable.id eq bike.id }) {
                it[fuelType] = bike.fuelType
                it[bikeTable.userId] = userId
                it[bodyType] = bike.bodyType
                it[brand] = bike.brand
                it[model] = bike.model
                it[modelYear] = bike.modelYear
                it[licensePlate] = bike.licensePlate
                it[mileage] = bike.mileage
                it[createdStamp] = bike.createdStamp
            }

            bike
        }

    override fun delete(id: Int): Boolean =
        transaction {
            bikeTable.deleteWhere { bikeTable.id eq id } > 0
        }

    override fun getAll(): List<bikeEntity> =
        transaction {
            bikeTable.selectAll().map { it.tobikeEntity() }
        }
}
