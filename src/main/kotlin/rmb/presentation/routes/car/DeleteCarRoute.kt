package rmb.presentation.routes.bike

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import rmb.application.usecases.bike.DeletebikeUsecase
import rmb.infrastructure.plugins.userId
import kotlin.text.toIntOrNull

fun Route.deletebikeRoute(deletebikeUsecase: DeletebikeUsecase) {
    authenticate("myAuth") {
        route("/bike") {
            delete("/delete/{id}") {
                val bikeId =
                    call.parameters["id"]?.toIntOrNull()
                        ?: return@delete call.respond(HttpStatusCode.BadRequest, "Invalid or missing id parameter")

                deletebikeUsecase(bikeId, call.userId)

                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}
