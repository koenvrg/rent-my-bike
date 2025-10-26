package rmb.presentation.routes.advertisement

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import rmb.application.usecases.advertisement.DeleteAdvertisementUsecase
import rmb.infrastructure.plugins.userId
import kotlin.text.toIntOrNull

fun Route.deleteAdvertisementRoute(deleteAdvertisementUsecase: DeleteAdvertisementUsecase) {
    authenticate("myAuth") {
        route("/advertisement") {
            delete("/delete/{id}") {
                val advertisementId =
                    call.parameters["id"]?.toIntOrNull()
                        ?: return@delete call.respond(HttpStatusCode.BadRequest, "Invalid or missing id parameter")

                deleteAdvertisementUsecase(advertisementId, call.userId)

                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}
