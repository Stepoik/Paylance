package stepan.gorokhov.paylance.features.home.projects.ui.detail

import stepan.gorokhov.paylance.features.home.projects.domain.models.Project

fun Project.toProjectVO() = ProjectVO(
    id = id,
    title = title,
    description = description,
    budget = budget.toString(),
    clientName = author.name,
    deadline = deadline.toString(),
    workType = "", // TODO: Сделать
    skills = listOf(),
    status = status,
    isRespond = isRespond
)