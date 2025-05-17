package stepan.gorokhov.paylance.features.home.projects.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import paylance.composeapp.generated.resources.Res
import paylance.composeapp.generated.resources.orders
import stepan.gorokhov.paylance.features.home.HomeRoute
import stepan.gorokhov.paylance.features.home.projects.ui.ProjectsRoute
import stepan.gorokhov.paylance.features.home.projects.ui.common.projects
import stepan.gorokhov.paylance.features.home.projects.ui.main.components.searchButton
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.spacer

@Composable
fun ProjectsMainScreen(navController: NavController) {
    val viewModel = koinViewModel<ProjectsMainViewModel>()

    LaunchedEffect(Unit) {
        viewModel.loadProjects()
        viewModel.effect.collect { effect ->
            when (effect) {
                is ProjectsMainEffect.NavigateProject -> navController.navigate(ProjectsRoute.Details(effect.id))
                is ProjectsMainEffect.NavigateProfile -> navController.navigate("${HomeRoute.Profile}")
            }
        }
    }

    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        is ProjectsMainState.ProjectsLoaded -> {
            ProjectsMainScreen(state = state, presenter = viewModel)
        }
        is ProjectsMainState.Error -> {
            Box(Modifier.fillMaxSize()) { Text("Ошибка") }
        }
        is ProjectsMainState.Loading -> {
            Box(Modifier.fillMaxSize()) { Text("Загрузка") }
        }
    }
}

@Composable
fun ProjectsMainScreen(state: ProjectsMainState.ProjectsLoaded, presenter: ProjectsMainPresenter) {
    BaseScaffold {
        LazyColumn(Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            spacer(20.dp)
            item {
                Text(
                    stringResource(Res.string.orders),
                    style = PaylanceTheme.typography.titleMedium,
                    color = PaylanceTheme.colors.onBackground,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
            spacer(20.dp)
            searchButton {  }
            projects(state.projects, onProjectClick = presenter::onProjectClicked)
        }
    }
}