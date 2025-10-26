package rmb.infrastructure.repositories

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import rmb.domain.entities.bikeEntity
import rmb.domain.entities.bikeImageEntity
import rmb.domain.repositories.bikeImageRepositoryInterface
import rmb.infrastructure.mappers.tobikeImageEntity
import rmb.infrastructure.tables.bikeImageTable

class bikeImageRepository() : bikeImageRepositoryInterface {
    override fun findBybikeId(bikeId: Int): List<bikeImageEntity> =
        transaction {
            bikeImageTable.selectAll().where { bikeImageTable.bikeId eq bikeId }
                .map { it.tobikeImageEntity() }
        }

    override fun deleteAllBybike(bike: bikeEntity): Boolean =
        transaction {
            bikeImageTable.deleteWhere { bikeImageTable.bikeId eq bike.id!! } > 0
        }

    override fun save(bikeImage: bikeImageEntity): bikeImageEntity =
        transaction {
            if (bikeImage.id == null) {
                val id =
                    bikeImageTable.insert {
                        it[bikeId] = bikeImage.bikeId
                        it[image] = bikeImage.image
                        it[weight] = bikeImage.weight
                    } get bikeImageTable.id

                return@transaction bikeImage.copy(id = id)
            }

            bikeImageTable.update({ bikeImageTable.id eq bikeImage.id }) {
                it[bikeId] = bikeImage.bikeId
                it[image] = bikeImage.image
                it[weight] = bikeImage.weight
            }

            bikeImage
        }
}
