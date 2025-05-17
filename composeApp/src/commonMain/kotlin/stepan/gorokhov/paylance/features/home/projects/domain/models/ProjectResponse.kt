package stepan.gorokhov.paylance.features.home.projects.domain.models

data class ProjectResponse(
    val id: String,
    val projectId: String,
    val ownerId: String,
    val freelancerId: String,
    val status: ResponseStatus
)

enum class ResponseStatus {
    ACCEPTED,
    REJECTED,
    WAIT_FOR_ACCEPT
}