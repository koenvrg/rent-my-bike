package rmb.presentation.routes.rentaltrip

import io.ktor.http.*
import io.ktor.server.auth.authenticate
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import rmb.application.usecases.rentaltrip.RegisterRentalTripUsecase
import rmb.presentation.dto.rentaltrip.RegisterRentalTrip
import rmb.presentation.mappers.toResponse

fun Route.registerRentalTripRoute(registerRentalTripUsecase: RegisterRentalTripUsecase) {
    authenticate("myAuth") {
        route("/rental/trip") {
            post("/register") {
                val request = call.receive<RegisterRentalTrip>()

                val trip = registerRentalTripUsecase(request)

                call.respond(HttpStatusCode.Created, trip.toResponse())
            }
        }
    }
}
