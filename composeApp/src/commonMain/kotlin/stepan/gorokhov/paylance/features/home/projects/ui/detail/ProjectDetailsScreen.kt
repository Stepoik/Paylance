package stepan.gorokhov.paylance.features.home.projects.ui.detail

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.spacer

@Composable
fun ProjectDetailsScreen(navController: NavController, projectId: String) {
    val viewModel = koinViewModel<ProjectDetailsViewModel>(parameters = { parametersOf(projectId) })
    LaunchedEffect(Unit) {
        viewModel.loadProject()
        viewModel.effect.collect {

        }
    }
    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        is ProjectDetailsState.ProjectLoaded -> {
            ProjectDetailsScreen(presenter = viewModel, state = state)
        }

        is ProjectDetailsState.Error -> {

        }

        else -> {

        }
    }
}

@Composable
fun ProjectDetailsScreen(
    presenter: ProjectDetailsPresenter,
    state: ProjectDetailsState.ProjectLoaded
) {
    BaseScaffold {
        LazyColumn {
            spacer(20.dp)
            title(state.project.title)
            spacer(20.dp)
            description(state.project.description)
            spacer(20.dp)
            budget(state.project.budget)
        }
    }
}

fun LazyListScope.title(title: String) {
    item {
        Text(
            title,
            color = PaylanceTheme.colors.onBackground,
            style = PaylanceTheme.typography.titleLarge
        )
    }
}

fun LazyListScope.description(description: String) {
    item {
        Text(
            description,
            color = PaylanceTheme.colors.onBackground,
            style = PaylanceTheme.typography.titleLarge
        )
    }
}

fun LazyListScope.budget(budget: String) {
    item {
        Text(
            budget,
            color = PaylanceTheme.colors.onBackground,
            style = PaylanceTheme.typography.titleLarge
        )
    }
}