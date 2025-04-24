package stepan.gorokhov.paylance.features.home.profile.domain

import kotlinx.coroutines.flow.Flow
import stepan.gorokhov.paylance.features.home.profile.domain.models.User

interface UserRepository {
    val isAuthorized: Flow<Boolean>

    suspend fun updateUser(): Result<Any?>

    suspend fun getUser(): Result<User>
}