package rmb.infrastructure.mappers

import org.jetbrains.exposed.sql.ResultRow
import rmb.domain.entities.bikeEntity
import rmb.infrastructure.tables.bikeTable

fun ResultRow.tobikeEntity() =
    bikeEntity(
        id = this[bikeTable.id],
        fuelType = this[bikeTable.fuelType],
        userId = this[bikeTable.userId],
        bodyType = this[bikeTable.bodyType],
        brand = this[bikeTable.brand],
        model = this[bikeTable.model],
        modelYear = this[bikeTable.modelYear],
        licensePlate = this[bikeTable.licensePlate],
        mileage = this[bikeTable.mileage],
        createdStamp = this[bikeTable.createdStamp],
    )
