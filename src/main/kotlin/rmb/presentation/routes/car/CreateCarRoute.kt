package rmb.presentation.routes.bike

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import rmb.application.usecases.bike.CreatebikeUsecase
import rmb.infrastructure.plugins.userId
import rmb.presentation.dto.bike.Createbike
import rmb.presentation.mappers.toResponse

fun Route.createbikeRoute(createbikeUsecase: CreatebikeUsecase) {
    authenticate("myAuth") {
        route("/bike") {
            post("/create") {
                val request = call.receive<Createbike>()

                val bike = createbikeUsecase(request, call.userId)

                call.respond(HttpStatusCode.Created, bike.toResponse())
            }
        }
    }
}
