package rmb.infrastructure.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import rmb.application.exceptions.*
import rmb.domain.exceptions.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<IllegalAccessException> { call, cause ->
            call.respond(HttpStatusCode.Unauthorized, mapOf("error" to cause.message))
        }

        exception<InvalidCredentialsException> { call, cause ->
            call.respond(HttpStatusCode.Conflict, mapOf("error" to cause.message))
        }

        exception<bikeAlreadyExistsException> { call, cause ->
            call.respond(HttpStatusCode.Conflict, mapOf("error" to cause.message))
        }

        exception<bikeNotFoundException> { call, cause ->
            call.respond(HttpStatusCode.NotFound, mapOf("error" to cause.message))
        }

        exception<bikeHasActiveRentalException> { call, cause ->
            call.respond(HttpStatusCode.Conflict, mapOf("error" to cause.message))
        }

        exception<InvalidUserException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, cause.message ?: "Invalid user")
        }

        exception<UserNotFoundException> { call, cause ->
            call.respond(HttpStatusCode.NotFound, mapOf("error" to cause.message))
        }

        exception<UserAlreadyExistsException> { call, cause ->
            call.respond(HttpStatusCode.Conflict, mapOf("error" to cause.message))
        }

        exception<AdvertisementAlreadyExistsForbikeException> { call, cause ->
            call.respond(HttpStatusCode.Conflict, mapOf("error" to cause.message))
        }

        exception<AdvertisementNotFoundException> { call, cause ->
            call.respond(HttpStatusCode.NotFound, mapOf("error" to cause.message))
        }

        exception<AdvertisementHasActiveRentalException> { call, cause ->
            call.respond(HttpStatusCode.Conflict, mapOf("error" to cause.message))
        }

        exception<RentalNotFoundException> { call, cause ->
            call.respond(HttpStatusCode.NotFound, mapOf("error" to cause.message))
        }

        exception<RentalNotPendingException> { call, cause ->
            call.respond(HttpStatusCode.Conflict, mapOf("error" to cause.message))
        }

        exception<RentalCannotBeCancelledException> { call, cause ->
            call.respond(HttpStatusCode.Conflict, mapOf("error" to cause.message))
        }

        exception<Throwable> { call, cause ->
            cause.printStackTrace()
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "Internal server error"))
        }
    }
}
