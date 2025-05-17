package stepan.gorokhov.paylance.features.home.profile.domain

import kotlinx.coroutines.flow.Flow
import stepan.gorokhov.paylance.features.home.profile.domain.models.FreelancerInfo
import stepan.gorokhov.paylance.features.home.profile.domain.models.NewFreelancerInfo
import stepan.gorokhov.paylance.features.home.profile.domain.models.User

interface UserRepository {
    val isAuthorized: Flow<Boolean>

    suspend fun updateUser(): Result<Any?>

    suspend fun getUser(): Result<User>

    suspend fun updateUser(user: User): Result<Any?>

    suspend fun getFreelancer(): Result<FreelancerInfo>

    suspend fun updateFreelancer(info: NewFreelancerInfo): Result<Any?>

    suspend fun getFreelancerById(freelancerId: String): Result<FreelancerInfo>
}