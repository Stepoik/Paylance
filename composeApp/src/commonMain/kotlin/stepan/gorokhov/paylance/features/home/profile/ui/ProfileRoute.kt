package stepan.gorokhov.paylance.features.home.profile.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable
import stepan.gorokhov.paylance.features.home.HomeRoute
import stepan.gorokhov.paylance.features.home.profile.ui.edit.EditProfileScreen
import stepan.gorokhov.paylance.features.home.profile.ui.freelancerEdit.FreelancerEditScreen
import stepan.gorokhov.paylance.features.home.profile.ui.main.ProfileScreen

@Serializable
sealed class ProfileRoute {
    @Serializable
    data object Main : ProfileRoute()

    @Serializable
    data object Edit : ProfileRoute()

    @Serializable
    data object EditFreelancer : ProfileRoute()
}

fun NavGraphBuilder.profile(navController: NavController) {
    navigation<HomeRoute.Profile>(startDestination = ProfileRoute.Main) {
        composable<ProfileRoute.Main> {
            ProfileScreen(navController)
        }
        composable<ProfileRoute.Edit> {
            EditProfileScreen(navController)
        }
        composable<ProfileRoute.EditFreelancer> {
            FreelancerEditScreen(navController)
        }
    }
}