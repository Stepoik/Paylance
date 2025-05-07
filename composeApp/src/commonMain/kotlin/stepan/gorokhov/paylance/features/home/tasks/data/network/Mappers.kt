package stepan.gorokhov.paylance.features.home.tasks.data.network

import kotlinx.datetime.LocalDateTime
import stepan.gorokhov.paylance.core.time.now
import stepan.gorokhov.paylance.features.home.tasks.data.network.models.AuthorDto
import stepan.gorokhov.paylance.features.home.tasks.data.network.models.CreateTaskRequest
import stepan.gorokhov.paylance.features.home.tasks.data.network.models.TaskDto
import stepan.gorokhov.paylance.features.home.tasks.domain.models.Author
import stepan.gorokhov.paylance.features.home.tasks.domain.models.Task

fun TaskDto.toDomain(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        author = author.toDomain(),
        createdAt = createdAt
    )
}

fun AuthorDto.toDomain(): Author {
    return Author(
        id = id,
        name = name,
        imageURL = imageURL
    )
}

fun Task.toCreateRequest(): CreateTaskRequest {
    return CreateTaskRequest(
        title = title,
        description = description,
        budget = 0.0,
        deadline = LocalDateTime.now()
    )
}