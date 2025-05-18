package stepan.gorokhov.paylance.features.home.projects.ui.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import stepan.gorokhov.paylance.uikit.components.textfields.TextFieldWithIcon

fun LazyListScope.searchTextField(value: String, onValueChanged: (String) -> Unit) {
    item("search_textfield") {
        TextFieldWithIcon(
            icon = Icons.Default.Search,
            value = value,
            onValueChanged = onValueChanged,
            enabled = true,
            placeholder = "Поиск теста...",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

fun LazyListScope.searchButton(onClick: () -> Unit) {
    item("search_textfield") {
        SearchButton(onClick = onClick, modifier = Modifier.fillMaxWidth())
    }
}

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