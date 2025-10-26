package rmb.infrastructure.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import rmb.domain.entities.BodyType
import rmb.domain.entities.FuelType

object bikeTable : Table("bike") {
    val id = integer("id").autoIncrement()
    val fuelType = enumerationByName("fuel_tye", 20, FuelType::class)
    val userId = integer("user_id").references(UserTable.id)
    val bodyType = enumerationByName("body_type", 20, BodyType::class)
    val brand = varchar("brand", 50)
    val model = varchar("model", 50)
    val modelYear = varchar("model_year", 50)
    val licensePlate = varchar("license_plate", 20).uniqueIndex()
    val mileage = varchar("mileage", 50)
    val createdStamp = datetime("created_stamp")
    override val primaryKey = PrimaryKey(id)
}
