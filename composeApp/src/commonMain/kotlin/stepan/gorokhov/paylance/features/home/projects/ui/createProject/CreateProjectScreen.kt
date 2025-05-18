package stepan.gorokhov.paylance.features.home.projects.ui.createProject

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import paylance.composeapp.generated.resources.Res
import paylance.composeapp.generated.resources.*
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.MyProjectsRoute
import stepan.gorokhov.paylance.uikit.components.BaseButton
import stepan.gorokhov.paylance.uikit.components.BaseScaffold
import stepan.gorokhov.paylance.uikit.components.LoadingButton
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer
import stepan.gorokhov.paylance.uikit.components.spacer
import stepan.gorokhov.paylance.uikit.components.textfields.AreaTextField
import stepan.gorokhov.paylance.uikit.components.textfields.BaseTextField

@Composable
fun CreateProjectScreen(navController: NavController, title: String, description: String) {
    val viewModel =
        koinViewModel<CreateProjectViewModel>(parameters = { parametersOf(title, description) })
    val state = viewModel.state.collectAsStateWithLifecycle().value
    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is CreateProjectEffect.NavigateBack -> navController.navigateUp()
                is CreateProjectEffect.NavigateProject ->
                    navController.navigate(MyProjectsRoute.Details(it.id))

                is CreateProjectEffect.NavigateGenerateProject ->
                    navController.navigate(MyProjectsRoute.GenerateProject)
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
            skillsSection(presenter = presenter, state = state)
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

fun LazyListScope.skillsSection(presenter: CreateProjectPresenter, state: CreateProjectState) {
    item {
        Text(
            text = "Требуемые навыки",
            style = PaylanceTheme.typography.titleMedium,
            color = PaylanceTheme.colors.onBackground
        )
    }

    item {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(state.skills) { skill ->
                AssistChip(
                    onClick = { },
                    label = { Text(skill) },
                    trailingIcon = {
                        IconButton(
                            onClick = { presenter.removeSkill(skill) },
                            modifier = Modifier.size(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Удалить навык",
                                tint = PaylanceTheme.colors.onSurface,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                )
            }
        }
    }

    item {
        var newSkill by remember { mutableStateOf("") }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BaseTextField(
                value = newSkill,
                placeholder = "Новый навык",
                onValueChanged = { newSkill = it },
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = {
                    if (newSkill.isNotBlank()) {
                        presenter.addSkill(newSkill)
                        newSkill = ""
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить навык",
                    tint = PaylanceTheme.colors.primary
                )
            }
        }
    }
}

fun LazyListScope.createButton(presenter: CreateProjectPresenter, state: CreateProjectState) {
    item {
        Column {
            BaseButton(
                text = "Сгенерировать задачу",
                onClick = presenter::onClickGenerateProject,
                modifier = Modifier.fillMaxWidth()
            )
            VerticalSpacer(20.dp)
            if (state.isCreating) {
                LoadingButton(Modifier.fillMaxWidth())
            } else {
                BaseButton(
                    text = stringResource(Res.string.create),
                    onClick = presenter::onClickCreate,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !state.isCreating
                )
            }
        }
    }
}