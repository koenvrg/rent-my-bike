package rmb.presentation.routes.user

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import rmb.application.usecases.user.SignupUsecase
import rmb.presentation.dto.user.CreateUser
import rmb.presentation.mappers.toResponse

fun Route.userSignupRoute(signupUsecase: SignupUsecase) {
    route("/signup") {
        post {
            val request = call.receive<CreateUser>()

            val created = signupUsecase(request)

            call.respond(HttpStatusCode.Created, created.toResponse())
        }
    }
}
