package rmb.presentation.routes.advertisement

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import rmb.application.usecases.advertisement.CreateAdvertisementUsecase
import rmb.infrastructure.plugins.userId
import rmb.presentation.dto.advertisement.CreateAdvertisement
import rmb.presentation.mappers.toResponse

fun Route.createAdvertisementRoute(createAdvertisementUsecase: CreateAdvertisementUsecase) {
    authenticate("myAuth") {
        route("/advertisement") {
            post("/create") {
                val request = call.receive<CreateAdvertisement>()

                val created = createAdvertisementUsecase(request, call.userId)

                call.respond(HttpStatusCode.Created, created.toResponse())
            }
        }
    }
}
