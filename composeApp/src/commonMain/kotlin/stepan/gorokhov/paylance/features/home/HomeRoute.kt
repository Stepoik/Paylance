package stepan.gorokhov.paylance.features.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import stepan.gorokhov.paylance.uikit.images.Work

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
            get() = Icons.Work
    }

    @Serializable
    data object Notifications : HomeRoute {
        override val icon: ImageVector
            get() = Icons.Default.Notifications
    }

    @Serializable
    data object Chats : HomeRoute {
        override val icon: ImageVector
            get() = Icons.Default.Email
    }
}