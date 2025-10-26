package rmb.presentation.routes.bike

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import rmb.application.usecases.bike.GetbikeUsecase
import rmb.presentation.mappers.toResponse
import kotlin.text.toIntOrNull

fun Route.getbikeRoute(getbikeUsecase: GetbikeUsecase) {
    authenticate("myAuth") {
        route("/bike") {
            get("/show/{id}") {
                val bikeId =
                    call.parameters["id"]?.toIntOrNull()
                        ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid or missing id parameter")

                val bike = getbikeUsecase(bikeId)

                call.respond(HttpStatusCode.OK, bike.toResponse())
            }
        }
    }
}
