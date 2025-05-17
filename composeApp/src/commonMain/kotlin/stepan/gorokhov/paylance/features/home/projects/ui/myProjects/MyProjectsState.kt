package stepan.gorokhov.paylance.features.home.projects.ui.myProjects

import kotlinx.collections.immutable.ImmutableList
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.projects.ui.common.ProjectPreview

sealed class MyProjectsState {
    data object Loading : MyProjectsState()
    data class ProjectsLoaded(
        val projects: ImmutableList<ProjectPreview>,
        val errorMessage: ErrorMessage?
    ) : MyProjectsState()

    data class Error(val errorMessage: ErrorMessage) : MyProjectsState()
}

sealed class MyProjectsEffect {
    data object NavigateCreateProject : MyProjectsEffect()
    data class NavigateProject(val id: String) : MyProjectsEffect()
}