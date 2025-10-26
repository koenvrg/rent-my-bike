package rmb.infrastructure.plugins

import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.principal
import rmb.application.exceptions.InvalidUserException

val ApplicationCall.userId: Int
    get() =
        principal<UserIdPrincipal>()?.name?.toIntOrNull()
            ?: throw InvalidUserException("Invalid or missing userId in token")
