package stepan.gorokhov.paylance.uikit.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.unit.Dp

fun LazyListScope.spacer(dp: Dp) {
    item {
        VerticalSpacer(dp)
    }
}