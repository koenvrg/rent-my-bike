package rmb.presentation.routes.advertisement

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import rmb.application.usecases.advertisement.UpdateAdvertisementUsecase
import rmb.presentation.dto.advertisement.UpdateAdvertisement
import rmb.presentation.mappers.toResponse
import kotlin.text.toIntOrNull

fun Route.updateAdvertisementRoute(updateAdvertisementUsecase: UpdateAdvertisementUsecase) {
    authenticate("myAuth") {
        route("/advertisement") {
            put("/update/{id}") {
                val advertisementId =
                    call.parameters["id"]?.toIntOrNull()
                        ?: return@put call.respond(HttpStatusCode.BadRequest, "Invalid or missing id parameter")

                val advertisementRequest = call.receive<UpdateAdvertisement>()
                val requestWithId = advertisementRequest.copy(bikeId = advertisementId)

                val updatedAdvertisement = updateAdvertisementUsecase(requestWithId)
                call.respond(HttpStatusCode.OK, updatedAdvertisement.toResponse())
            }
        }
    }
}
