package stepan.gorokhov.paylance.uikit.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier

fun LazyListScope.horizontalDivider() {
    item {
        HorizontalDivider(Modifier.fillMaxWidth())
    }
}