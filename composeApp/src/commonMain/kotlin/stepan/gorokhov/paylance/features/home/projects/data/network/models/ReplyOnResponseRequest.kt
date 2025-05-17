package stepan.gorokhov.paylance.features.home.projects.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class ReplyOnResponseRequest(
    val responseId: String,
    val replyType: ReplyTypeDto
)
@Serializable
enum class ReplyTypeDto {
    ACCEPTED,
    REJECTED,
}

