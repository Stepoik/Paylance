package stepan.gorokhov.paylance.features.home.projects.ui.myProjects.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import stepan.gorokhov.paylance.coreui.SingleLaunch
import stepan.gorokhov.paylance.features.home.projects.ui.common.projects
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.MyProjectsPresenter
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.MyProjectsState

@Composable
fun FreelancerProjectsScreen(presenter: MyProjectsPresenter, state: MyProjectsState) {
    SingleLaunch {
        presenter.loadProjects()
    }
    when (state) {
        is MyProjectsState.ProjectsLoaded -> {
            FreelancerProjectScreen(presenter, state)
        }

        else -> {
            Text("Гружусь")
        }
    }
}

@Composable
fun FreelancerProjectScreen(presenter: MyProjectsPresenter, state: MyProjectsState.ProjectsLoaded) {
    Column {
        LazyColumn(Modifier.fillMaxSize()) {
            projects(state.projects, onProjectClick = presenter::openProject)
        }
    }
}