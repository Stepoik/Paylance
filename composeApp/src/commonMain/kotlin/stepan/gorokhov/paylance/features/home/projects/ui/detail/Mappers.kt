package stepan.gorokhov.paylance.features.home.projects.ui.detail

import stepan.gorokhov.paylance.features.home.projects.domain.models.Project

fun Project.toProjectVO() = ProjectVO(
    title = title,
    description = description,
    budget = budget.toString()
)