package stepan.gorokhov.paylance.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gorokhov.stepan.paylance.uikit.PaylanceTheme

@Composable
fun BaseScaffold(
    topBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = topBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            Modifier.background(PaylanceTheme.colors.background).padding(it)
        ) {
            content()
        }
    }
}