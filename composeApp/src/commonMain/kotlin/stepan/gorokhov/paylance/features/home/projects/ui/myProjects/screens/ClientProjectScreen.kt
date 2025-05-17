package stepan.gorokhov.paylance.features.home.projects.ui.myProjects.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import gorokhov.stepan.paylance.uikit.components.BaseButton
import org.jetbrains.compose.resources.stringResource
import paylance.composeapp.generated.resources.Res
import paylance.composeapp.generated.resources.create_project
import paylance.composeapp.generated.resources.create_project_agitation
import stepan.gorokhov.paylance.coreui.SingleLaunch
import stepan.gorokhov.paylance.features.home.projects.ui.common.projects
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.MyProjectsPresenter
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.MyProjectsState
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer

@Composable
fun ClientProjectScreen(presenter: MyProjectsPresenter, state: MyProjectsState) {
    SingleLaunch {
        presenter.loadProjects()
    }
    when (state) {
        is MyProjectsState.ProjectsLoaded -> {
            ClientProjectScreen(presenter, state)
        }

        else -> {
            Text("Гружусь")
        }
    }
}

@Composable
fun ClientProjectScreen(presenter: MyProjectsPresenter, state: MyProjectsState.ProjectsLoaded) {
    Column {
        if (state.projects.isEmpty()) {
            CreateProjectAgitation(
                onClick = presenter::createProject,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            LazyColumn(Modifier.fillMaxSize()) {
                projects(state.projects, onProjectClick = presenter::openProject)
            }
        }
    }
}

@Composable
fun CreateProjectAgitation(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                stringResource(Res.string.create_project_agitation),
                style = PaylanceTheme.typography.titleMedium,
                color = PaylanceTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 30.dp)
            )
            VerticalSpacer(20.dp)
            BaseButton(stringResource(Res.string.create_project), onClick = onClick)
        }
    }
}