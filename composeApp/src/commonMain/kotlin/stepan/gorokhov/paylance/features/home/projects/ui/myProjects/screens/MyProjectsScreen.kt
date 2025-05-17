package stepan.gorokhov.paylance.features.home.projects.ui.myProjects.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import kotlinx.coroutines.flow.merge
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import paylance.composeapp.generated.resources.Res
import paylance.composeapp.generated.resources.client
import paylance.composeapp.generated.resources.freelancer
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.ClientProjectsViewModel
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.FreelancerProjectsViewModel
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.MyProjectsEffect
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.MyProjectsRoute
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.MyProjectsState
import stepan.gorokhov.paylance.uikit.components.BaseScaffold

@Composable
fun MyProjectsScreen(navController: NavController) {
    val clientViewModel = koinViewModel<ClientProjectsViewModel>()
    val freelancerViewModel = koinViewModel<FreelancerProjectsViewModel>()
    LaunchedEffect(Unit) {
        val clientEffect = clientViewModel.effect
        val freelancerEffect = freelancerViewModel.effect
        merge(clientEffect, freelancerEffect).collect { effect ->
            when (effect) {
                is MyProjectsEffect.NavigateCreateProject -> navController.navigate(MyProjectsRoute.CreateProject)
                is MyProjectsEffect.NavigateProject -> navController.navigate(
                    MyProjectsRoute.Details(
                        effect.id
                    )
                )
            }
        }
    }
    MyProjectsScreen(clientViewModel = clientViewModel, freelancerViewModel = freelancerViewModel)
}

@Composable
fun MyProjectsScreen(
    clientViewModel: ClientProjectsViewModel,
    freelancerViewModel: FreelancerProjectsViewModel
) {
    var selectedTab by remember {
        mutableStateOf(0)
    }
    BaseScaffold {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = PaylanceTheme.colors.background,
            indicator = { tabPositions ->
                if (selectedTab < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        color = PaylanceTheme.colors.primary,
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab])
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, text = {
                TabText(
                    stringResource(Res.string.client)
                )
            })

            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, text = {
                TabText(
                    stringResource(Res.string.freelancer)
                )
            })
        }
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
        LaunchedEffect(selectedTab) {
            pagerState.animateScrollToPage(selectedTab)
        }

        HorizontalPager(state = pagerState, userScrollEnabled = false) { state ->
            when (state) {
                0 -> {
                    ClientProjectScreen(
                        presenter = clientViewModel,
                        state = clientViewModel.state.collectAsStateWithLifecycle().value
                    )
                }

                1 -> {
                    FreelancerProjectsScreen(
                        presenter = freelancerViewModel,
                        state = freelancerViewModel.state.collectAsStateWithLifecycle().value
                    )
                }
            }
        }
    }
}

@Composable
private fun TabText(text: String) {
    Text(
        text = text,
        style = PaylanceTheme.typography.titleMedium,
        color = PaylanceTheme.colors.onBackground
    )
}