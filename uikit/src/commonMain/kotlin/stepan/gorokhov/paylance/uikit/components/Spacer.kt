package stepan.gorokhov.paylance.uikit.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpacer(dp: Dp) {
    Spacer(Modifier.padding(top = dp))
}

@Composable
fun HorizontalSpacer(dp: Dp) {
    Spacer(Modifier.padding(start = dp))
}