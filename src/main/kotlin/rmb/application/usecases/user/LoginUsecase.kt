package rmb.application.usecases.user

import org.mindrot.jbcrypt.BCrypt
import rmb.application.exceptions.InvalidCredentialsException
import rmb.domain.entities.UserEntity
import rmb.domain.repositories.UserRepositoryInterface

class LoginUsecase(
    private val userRepository: UserRepositoryInterface,
) {
    operator fun invoke(
        email: String,
        password: String,
    ): UserEntity {
        val user =
            userRepository.findByEmail(email)
                ?: throw InvalidCredentialsException("Invalid email or password")

        if (!BCrypt.checkpw(password, user.password)) {
            throw InvalidCredentialsException("Invalid email or password")
        }

        return user
    }
}
