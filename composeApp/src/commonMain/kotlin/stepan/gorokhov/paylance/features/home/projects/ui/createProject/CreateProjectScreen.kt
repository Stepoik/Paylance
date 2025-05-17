package stepan.gorokhov.paylance.features.home.projects.ui.createProject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import gorokhov.stepan.paylance.uikit.components.BaseButton
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import paylance.composeapp.generated.resources.Res
import paylance.composeapp.generated.resources.budget_rubles
import paylance.composeapp.generated.resources.create
import paylance.composeapp.generated.resources.creating_project
import paylance.composeapp.generated.resources.description
import paylance.composeapp.generated.resources.title
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.MyProjectsRoute
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.spacer
import stepan.gorokhov.paylance.uikit.components.textfields.AreaTextField
import stepan.gorokhov.paylance.uikit.components.textfields.BaseTextField

@Composable
fun CreateProjectScreen(navController: NavController) {
    val viewModel = koinViewModel<CreateProjectViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is CreateProjectEffect.NavigateBack -> navController.navigateUp()
                is CreateProjectEffect.NavigateProject -> navController.navigate(MyProjectsRoute.Details(it.id))
            }
        }
    }
    CreateProjectScreen(viewModel, state)
}

@Composable
fun CreateProjectScreen(presenter: CreateProjectPresenter, state: CreateProjectState) {
    BaseScaffold {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(horizontal = 20.dp).fillMaxSize()
        ) {
            spacer(0.dp)
            item {
                Text(
                    stringResource(Res.string.creating_project),
                    style = PaylanceTheme.typography.titleLarge,
                    color = PaylanceTheme.colors.onBackground
                )
            }
            titleTextField(presenter = presenter, state = state)
            descriptionTextField(presenter = presenter, state = state)
            budgetTextField(presenter = presenter, state = state)
            createButton(presenter = presenter, state = state)
        }
    }
}

fun LazyListScope.titleTextField(presenter: CreateProjectPresenter, state: CreateProjectState) {
    item {
        BaseTextField(
            value = state.title,
            placeholder = stringResource(Res.string.title),
            onValueChanged = presenter::setTitle,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

fun LazyListScope.descriptionTextField(
    presenter: CreateProjectPresenter,
    state: CreateProjectState
) {
    item {
        AreaTextField(
            value = state.description,
            placeholder = stringResource(Res.string.description),
            onValueChanged = presenter::setDescription,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

fun LazyListScope.budgetTextField(presenter: CreateProjectPresenter, state: CreateProjectState) {
    item {
        BaseTextField(
            value = state.budget,
            placeholder = stringResource(Res.string.budget_rubles),
            onValueChanged = {
                if (it.replace(',', '.').toDoubleOrNull() != null || it.isEmpty()) {
                    presenter.setBudget(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

fun LazyListScope.createButton(presenter: CreateProjectPresenter, state: CreateProjectState) {
    item {
        BaseButton(
            stringResource(Res.string.create),
            onClick = presenter::onClickCreate,
            modifier = Modifier.fillMaxWidth()
        )
    }
}