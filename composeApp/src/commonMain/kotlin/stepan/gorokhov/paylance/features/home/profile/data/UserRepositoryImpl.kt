package stepan.gorokhov.paylance.features.home.profile.data

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import stepan.gorokhov.paylance.features.home.profile.data.network.UserApi
import stepan.gorokhov.paylance.features.home.profile.domain.UserRepository
import stepan.gorokhov.paylance.features.home.profile.domain.models.FreelancerInfo
import stepan.gorokhov.paylance.features.home.profile.domain.models.NewFreelancerInfo
import stepan.gorokhov.paylance.features.home.profile.domain.models.User

class FirebaseUserRepository(
    private val userApi: UserApi
) : UserRepository {
    private val auth = Firebase.auth

    override val isAuthorized: Flow<Boolean>
        get() = auth.authStateChanged.map { it != null }

    override suspend fun refreshUser(): Result<Any?> {
        return runCatching {
            val currentUser = auth.currentUser!!
            auth.updateCurrentUser(currentUser)
        }
    }

    override suspend fun updateUser(user: User): Result<Any?> {
       return runCatching {
           val currentUser = auth.currentUser
           currentUser?.updateEmail(user.email)
           currentUser?.updateProfile(displayName = user.name)
       }
    }

    override suspend fun getUser(): Result<User> {
        return runCatching {
            auth.currentUser!!.toDomain()
        }
    }

    override suspend fun getFreelancer(): Result<FreelancerInfo> {
        return runCatching {
            val id = auth.currentUser!!.uid
            userApi.getFreelancer(id).toFreelancerInfo()
        }
    }

    override suspend fun updateFreelancer(info: NewFreelancerInfo): Result<Any?> {
        return runCatching {
            val request = info.toUpdateFreelancerRequest()
            userApi.updateFreelancerInfo(request)
        }
    }

    override suspend fun getFreelancerById(freelancerId: String): Result<FreelancerInfo> {
        return runCatching {
            userApi.getFreelancer(freelancerId).toFreelancerInfo()
        }
    }
}