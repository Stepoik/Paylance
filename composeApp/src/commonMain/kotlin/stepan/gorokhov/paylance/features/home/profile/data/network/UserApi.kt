package stepan.gorokhov.paylance.features.home.profile.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import stepan.gorokhov.paylance.features.home.profile.data.network.models.FreelancerDto
import stepan.gorokhov.paylance.features.home.profile.data.network.models.UpdateFreelancerRequest
import stepan.gorokhov.paylance.network.Constants

private const val BASE_URL = "${Constants.BASE_URL}/users"

class UserApi(
    private val httpClient: HttpClient
) {
    suspend fun getFreelancer(id: String): FreelancerDto {
        return httpClient.get("$BASE_URL/freelancer/$id").body()
    }

    suspend fun updateFreelancerInfo(request: UpdateFreelancerRequest) {
        httpClient.put(BASE_URL) {
            setBody(request)
        }
    }
}