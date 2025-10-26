package rmb.infrastructure.tables

import org.jetbrains.exposed.sql.Table

object bikeImageTable : Table("bike_image") {
    val id = integer("id").autoIncrement()
    val bikeId = integer("bike_id").references(bikeTable.id)
    val image = largeText("image")
    val weight = integer("weight")
    override val primaryKey = PrimaryKey(id)
}
