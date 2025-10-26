package rmb.infrastructure.plugins

import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import rmb.infrastructure.tables.*

fun initDatabase() {
    val dotenv = dotenv()

    val useLocaleDatabase = dotenv["USE_LOCALE_DATABASE"]?.toBoolean() ?: false

    if (useLocaleDatabase) {
        Database.connect(
            url = dotenv["DB_URL"],
            driver = "com.mysql.cj.jdbc.Driver",
            user = dotenv["DB_USER"],
            password = dotenv["DB_PASSWORD"],
        )
    } else {
        Database.connect(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;",
            driver = "org.h2.Driver",
            user = "root",
            password = "",
        )
    }

    transaction {
        SchemaUtils.createMissingTablesAndColumns(
            bikeTable,
            AddressTable,
            UserTable,
            AdvertisementTable,
            bikeImageTable,
            RentalTable,
            RentalTripTable,
        )
    }
}
