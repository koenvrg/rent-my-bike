package rmb.presentation.routes.user

import io.ktor.http.ContentType
import io.ktor.server.request.receive
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import kotlinx.serialization.json.Json
import rmb.application.auth.Authenticator
import rmb.application.usecases.user.LoginUsecase
import rmb.presentation.dto.user.LoginRequest

fun Route.userLoginRoute(
    loginUsecase: LoginUsecase,
    authenticator: Authenticator,
) {
    post("/login") {
        val request = call.receive<LoginRequest>()
        val user = loginUsecase(request.email, request.password)

        val token = authenticator.generateToken(user)
        call.respondText(
            Json.encodeToString(hashMapOf("token" to token)),
            ContentType.Application.Json,
        )
    }
}
