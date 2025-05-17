package stepan.gorokhov.paylance.features.home.projects.ui

import kotlinx.serialization.Serializable

@Serializable
sealed class ProjectsRoute {
    @Serializable
    data object Main : ProjectsRoute()

    @Serializable
    data class Details(val id: String) : ProjectsRoute()
}