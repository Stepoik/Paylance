package stepan.gorokhov.paylance.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.paylance.coreui.routing.popUpGraph
import stepan.gorokhov.paylance.features.ApplicationRoute

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel = koinViewModel<SplashViewModel>()
    LaunchedEffect(Unit) {
        viewModel.checkUserAuth()
        viewModel.effect.collect { effect ->
            when (effect) {
                is SplashEffect.NavigateAuth -> {
                    navController.navigate(ApplicationRoute.Auth) { popUpGraph() }
                }

                is SplashEffect.NavigateTests -> {
                    navController.navigate(ApplicationRoute.Home) { popUpGraph() }
                }
            }
        }
    }
}