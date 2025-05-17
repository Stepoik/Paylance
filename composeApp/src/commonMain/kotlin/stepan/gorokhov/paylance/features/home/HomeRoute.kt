package stepan.gorokhov.paylance.features.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeRoute {
    val icon: ImageVector

    @Serializable
    data object Profile : HomeRoute {
        override val icon: ImageVector
            get() = Icons.Default.Person
    }

    @Serializable
    data object Projects : HomeRoute {
        override val icon: ImageVector
            get() = Icons.Default.Home
    }

    @Serializable
    data object MyProjects : HomeRoute {
        override val icon: ImageVector
            get() = Icons.Default.Build
    }

    @Serializable
    data object Notifications : HomeRoute {
        override val icon: ImageVector
            get() = Icons.Default.Notifications
    }
}