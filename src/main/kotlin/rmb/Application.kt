package rmb

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import rmb.infrastructure.plugins.configureRouting
import rmb.infrastructure.plugins.configureSecurity
import rmb.infrastructure.plugins.configureStatusPages
import rmb.infrastructure.plugins.initDatabase

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    initDatabase()
    configureSecurity()
    configureRouting()
    configureStatusPages()
}
