package stepan.gorokhov.paylance.features.auth.domain.usecases

import stepan.gorokhov.paylance.features.auth.domain.AuthRepository
import stepan.gorokhov.paylance.features.auth.domain.models.SignInCredentials
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository

class SignInUseCase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    suspend fun execute(signInCredentials: SignInCredentials): Result<Any?> {
        authRepository.signIn(signInCredentials).onFailure { return Result.failure(it) }
        return userRepository.updateUser()
    }
}