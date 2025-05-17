package stepan.gorokhov.paylance.features.home.projects.data.network

import stepan.gorokhov.paylance.features.home.projects.data.network.models.ReplyTypeDto
import stepan.gorokhov.paylance.features.home.projects.data.network.models.ResponseDto
import stepan.gorokhov.paylance.features.home.projects.data.network.models.ResponseStatusDto
import stepan.gorokhov.paylance.features.home.projects.domain.models.ProjectResponse
import stepan.gorokhov.paylance.features.home.projects.domain.models.ReplyType
import stepan.gorokhov.paylance.features.home.projects.domain.models.ResponseStatus

fun ResponseDto.toDomain(): ProjectResponse {
    return ProjectResponse(
        id = id,
        projectId = projectId,
        ownerId = ownerId,
        freelancerId = freelancerId,
        status = status.toDomain()
    )
}

fun ResponseStatusDto.toDomain(): ResponseStatus {
    return when (this) {
        ResponseStatusDto.REJECTED -> ResponseStatus.REJECTED
        ResponseStatusDto.ACCEPTED -> ResponseStatus.ACCEPTED
        ResponseStatusDto.WAIT_FOR_ACCEPT -> ResponseStatus.WAIT_FOR_ACCEPT
    }
}

fun ReplyType.toDto(): ReplyTypeDto {
    return when (this) {
        ReplyType.REJECT -> ReplyTypeDto.REJECTED
        ReplyType.ACCEPT -> ReplyTypeDto.ACCEPTED
    }
}