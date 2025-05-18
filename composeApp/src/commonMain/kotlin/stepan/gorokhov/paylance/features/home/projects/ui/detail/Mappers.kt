package stepan.gorokhov.paylance.features.home.projects.ui.detail

import stepan.gorokhov.paylance.core.time.formatDateHoursMinutes
import stepan.gorokhov.paylance.core.time.fromUTC
import stepan.gorokhov.paylance.features.home.projects.domain.models.Project

fun Project.toProjectVO(userId: String) = ProjectVO(
    id = id,
    title = title,
    description = description,
    budget = "$budget₽",
    clientName = author.name,
    deadline = deadline.fromUTC().formatDateHoursMinutes(),
    workType = "", // TODO: Сделать
    skills = skills,
    status = status,
    isRespond = isRespond,
    isOwner = author.id == userId
)