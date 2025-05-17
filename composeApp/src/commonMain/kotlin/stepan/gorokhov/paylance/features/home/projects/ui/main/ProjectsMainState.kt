package stepan.gorokhov.paylance.features.home.projects.ui.main

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.profile.domain.models.User
import stepan.gorokhov.paylance.features.home.projects.domain.models.Author
import stepan.gorokhov.paylance.features.home.projects.ui.common.ProjectPreview

sealed class ProjectsMainState {
    data object Loading : ProjectsMainState()
    data class ProjectsLoaded(
        val user: User,
        val projects: ImmutableList<ProjectPreview> = persistentListOf()
    ) : ProjectsMainState()

    data class Error(val error: ErrorMessage) : ProjectsMainState()
}

sealed class ProjectsMainEffect {
    data object NavigateProfile : ProjectsMainEffect()
    data class NavigateProject(val id: String) : ProjectsMainEffect()
}