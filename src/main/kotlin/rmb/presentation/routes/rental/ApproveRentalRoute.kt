package rmb.presentation.routes.rental

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import rmb.application.usecases.rental.ApproveRentalUsecase
import rmb.presentation.mappers.toResponse
import kotlin.text.toIntOrNull

fun Route.approveRentalRoute(approveRentalUsecase: ApproveRentalUsecase) {
    authenticate("myAuth") {
        route("/rental") {
            post("/approve/{id}") {
                val rentalId =
                    call.parameters["id"]?.toIntOrNull()
                        ?: return@post call.respond(HttpStatusCode.BadRequest, "Invalid or missing id parameter")

                val rental = approveRentalUsecase(rentalId)

                call.respond(HttpStatusCode.OK, rental.toResponse())
            }
        }
    }
}
