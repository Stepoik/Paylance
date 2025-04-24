package stepan.gorokhov.paylance.features.auth.domain.usecases

import stepan.gorokhov.paylance.features.auth.domain.AuthRepository
import stepan.gorokhov.paylance.features.auth.domain.models.SignInCredentials
import stepan.gorokhov.paylance.features.auth.domain.models.SignUpCredentials

class SignUpUseCase(
    private val authRepository: AuthRepository,
    private val signInUseCase: SignInUseCase
) {
    suspend fun execute(signUpCredentials: SignUpCredentials): Result<Any?> {
        authRepository.signUp(signUpCredentials).onFailure { return Result.failure(it) }
        return signInUseCase.execute(signUpCredentials.toSignIn())
    }
}

private fun SignUpCredentials.toSignIn() = SignInCredentials(
    login = login,
    password = password
)