package stepan.gorokhov.paylance.features.auth.domain

import stepan.gorokhov.paylance.features.auth.domain.models.SignInCredentials
import stepan.gorokhov.paylance.features.auth.domain.models.SignUpCredentials

interface AuthRepository {
    suspend fun signIn(credentials: SignInCredentials): Result<Any?>

    suspend fun signUp(credentials: SignUpCredentials): Result<Any?>

    suspend fun logout(): Result<Any?>
}