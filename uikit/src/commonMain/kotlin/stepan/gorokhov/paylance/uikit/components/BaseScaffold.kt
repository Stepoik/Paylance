package stepan.gorokhov.paylance.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gorokhov.stepan.paylance.uikit.PaylanceTheme

@Composable
fun BaseScaffold(content: @Composable () -> Unit) {
    Scaffold {
        Column(Modifier.background(PaylanceTheme.colors.background).systemBarsPadding()) {
            content()
        }
    }
}