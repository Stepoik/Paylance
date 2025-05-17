package stepan.gorokhov.paylance.features.home.projects.data.network

import kotlinx.datetime.LocalDateTime
import stepan.gorokhov.paylance.core.time.now
import stepan.gorokhov.paylance.features.home.projects.data.network.models.AuthorDto
import stepan.gorokhov.paylance.features.home.projects.data.network.models.CreateProjectRequest
import stepan.gorokhov.paylance.features.home.projects.data.network.models.ProjectDto
import stepan.gorokhov.paylance.features.home.projects.data.network.models.ProjectStatusDto
import stepan.gorokhov.paylance.features.home.projects.domain.models.Author
import stepan.gorokhov.paylance.features.home.projects.domain.models.NewProject
import stepan.gorokhov.paylance.features.home.projects.domain.models.Project
import stepan.gorokhov.paylance.features.home.projects.domain.models.ProjectStatus

fun ProjectDto.toDomain(): Project {
    return Project(
        id = id,
        title = title,
        description = description,
        author = author.toDomain(),
        createdAt = createdAt,
        budget = budget,
        status = status.toDomain(),
        deadline = deadline,
        skills = listOf(),
        isRespond = isRespond
    )
}

fun ProjectStatusDto.toDomain(): ProjectStatus {
    return when (this) {
        ProjectStatusDto.OPEN -> ProjectStatus.OPEN
        ProjectStatusDto.CANCELLED -> ProjectStatus.CANCELLED
        ProjectStatusDto.COMPLETED -> ProjectStatus.COMPLETED
        ProjectStatusDto.IN_PROGRESS -> ProjectStatus.IN_PROGRESS
    }
}

fun AuthorDto.toDomain(): Author {
    return Author(
        id = id,
        name = name,
        imageURL = imageURL
    )
}

fun NewProject.toCreateRequest(): CreateProjectRequest {
    return CreateProjectRequest(
        title = title,
        description = description,
        budget = 0.0,
        deadline = LocalDateTime.now()
    )
}