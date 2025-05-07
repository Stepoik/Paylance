package stepan.gorokhov.paylance.features.home.tasks.ui.main.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import stepan.gorokhov.paylance.uikit.components.TextFieldWithIcon

@Composable
fun SearchButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    TextFieldWithIcon(
        icon = Icons.Default.Search,
        value = "",
        onValueChanged = {},
        enabled = false,
        placeholder = "Поиск теста...",
        modifier = modifier.clickable(onClick = onClick)
    )
}