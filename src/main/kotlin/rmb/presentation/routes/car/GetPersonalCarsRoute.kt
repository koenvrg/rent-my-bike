package rmb.presentation.routes.bike

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import rmb.application.usecases.bike.GetPersonalbikesUsecase
import rmb.infrastructure.plugins.userId
import rmb.presentation.mappers.toResponse

fun Route.getPersonalbikesRoute(getPersonalbikesUsecase: GetPersonalbikesUsecase) {
    authenticate("myAuth") {
        route("/personal") {
            get("/bikes") {
                val bikes = getPersonalbikesUsecase(call.userId)

                call.respond(HttpStatusCode.OK, bikes.map { it.toResponse() })
            }
        }
    }
}
