package rmb.infrastructure.mappers

import org.jetbrains.exposed.sql.ResultRow
import rmb.domain.entities.RentalTripEntity
import rmb.infrastructure.tables.RentalTripTable

fun ResultRow.toRentalTripEntity() =
    RentalTripEntity(
        id = this[RentalTripTable.id],
        rentalId = this[RentalTripTable.rentalId],
        startMileage = this[RentalTripTable.startMileage],
        endMileage = this[RentalTripTable.endMileage],
        startDate = this[RentalTripTable.startDate],
        endDate = this[RentalTripTable.endDate],
    )
