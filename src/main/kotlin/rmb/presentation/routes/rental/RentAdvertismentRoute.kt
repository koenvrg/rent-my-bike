package rmb.presentation.routes.rental

import io.ktor.http.*
import io.ktor.server.auth.authenticate
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import rmb.application.usecases.rental.RentAdvertisementUsecase
import rmb.infrastructure.plugins.userId
import rmb.presentation.dto.rental.RentAdvertisement
import rmb.presentation.mappers.toResponse

fun Route.rentAdvertisementRoute(rentAdvertisementUsecase: RentAdvertisementUsecase) {
    authenticate("myAuth") {
        route("/rent") {
            post("/advertisement") {
                val request = call.receive<RentAdvertisement>()

                val rental = rentAdvertisementUsecase(request, call.userId)

                call.respond(HttpStatusCode.Created, rental.toResponse())
            }
        }
    }
}
