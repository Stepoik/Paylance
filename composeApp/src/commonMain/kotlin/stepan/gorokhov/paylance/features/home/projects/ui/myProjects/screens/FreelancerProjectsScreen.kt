package stepan.gorokhov.paylance.features.home.projects.ui.myProjects.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import stepan.gorokhov.paylance.coreui.SingleLaunch
import stepan.gorokhov.paylance.features.home.projects.ui.common.projects
import stepan.gorokhov.paylance.features.home.projects.ui.common.projectListSkeleton
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
        is MyProjectsState.Loading -> {
            FreelancerProjectSkeleton()
        }
        is MyProjectsState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.errorMessage.message,
                    style = PaylanceTheme.typography.bodyLarge,
                    color = PaylanceTheme.colors.error
                )
            }
        }
    }
}

@Composable
private fun FreelancerProjectSkeleton() {
    Column {
        LazyColumn(Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            projectListSkeleton()
        }
    }
}

@Composable
fun FreelancerProjectScreen(presenter: MyProjectsPresenter, state: MyProjectsState.ProjectsLoaded) {
    Column {
        LazyColumn(Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            projects(state.projects, onProjectClick = presenter::openProject)
        }
    }
}