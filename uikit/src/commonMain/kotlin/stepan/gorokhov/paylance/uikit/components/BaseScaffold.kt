package stepan.gorokhov.paylance.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
    usePadding: Boolean = true,
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = topBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column(
            Modifier.background(PaylanceTheme.colors.background)
                .requiredPadding(usePadding = usePadding, padding = padding)
        ) {
            content()
        }
    }
}

private fun Modifier.requiredPadding(padding: PaddingValues, usePadding: Boolean): Modifier {
    return if (usePadding) {
        this then Modifier.padding(padding)
    } else this
}