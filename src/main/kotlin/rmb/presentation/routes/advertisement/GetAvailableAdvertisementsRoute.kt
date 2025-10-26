package rmb.presentation.routes.advertisement

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import rmb.application.usecases.advertisement.GetAvailableAdvertisementsUsecase
import rmb.presentation.mappers.toResponse

fun Route.getAvailableAdvertisementsRoute(getAvailableAdvertisementsUsecase: GetAvailableAdvertisementsUsecase) {
    route("/advertisement") {
        get("/all") {
            val advertisements = getAvailableAdvertisementsUsecase()

            call.respond(HttpStatusCode.OK, advertisements.map { it.toResponse() })
        }
    }
}
