package rmb.presentation.routes.bike

import io.ktor.http.*
import io.ktor.server.auth.authenticate
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import rmb.application.usecases.bike.UpdatebikeUsecase
import rmb.infrastructure.plugins.userId
import rmb.presentation.dto.bike.Updatebike
import rmb.presentation.mappers.toResponse

fun Route.updatebikeRoute(updatebikeUsecase: UpdatebikeUsecase) {
    authenticate("myAuth") {
        put("/bike/update/{id}") {
            val bikeId =
                call.parameters["id"]?.toIntOrNull()
                    ?: return@put call.respond(HttpStatusCode.BadRequest, "Invalid or missing id parameter")

            val bikeRequest = call.receive<Updatebike>()
            val requestWithId = bikeRequest.copy(id = bikeId)

            val updatedbike = updatebikeUsecase(requestWithId, call.userId)
            call.respond(HttpStatusCode.OK, updatedbike.toResponse())
        }
    }
}
