package rmb.infrastructure.mappers

import org.jetbrains.exposed.sql.ResultRow
import rmb.domain.entities.bikeImageEntity
import rmb.infrastructure.tables.bikeImageTable

fun ResultRow.tobikeImageEntity() =
    bikeImageEntity(
        id = this[bikeImageTable.id],
        bikeId = this[bikeImageTable.bikeId],
        image = this[bikeImageTable.image],
        weight = this[bikeImageTable.weight],
    )
