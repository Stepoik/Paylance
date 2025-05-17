package stepan.gorokhov.paylance.features.home.projects.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    val id: String,
    val projectId: String,
    val ownerId: String,
    val freelancerId: String,
    val status: ResponseStatusDto
)

@Serializable
enum class ResponseStatusDto {
    @SerialName("accepted")
    ACCEPTED,

    @SerialName("rejected")
    REJECTED,

    @SerialName("wait_for_accept")
    WAIT_FOR_ACCEPT
}