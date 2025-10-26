package rmb.infrastructure.mappers

import org.jetbrains.exposed.sql.ResultRow
import rmb.domain.entities.RentalEntity
import rmb.infrastructure.tables.RentalTable

fun ResultRow.toRentalEntity() =
    RentalEntity(
        id = this[RentalTable.id],
        userId = this[RentalTable.userId],
        advertisementId = this[RentalTable.advertisementId],
        rentalStatus = this[RentalTable.rentalStatus],
        returningDate = this[RentalTable.returningDate],
        pickUpDate = this[RentalTable.pickUpDate],
    )
