package stepan.gorokhov.paylance.features.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import gorokhov.stepan.paylance.uikit.PaylanceTheme

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize().background(PaylanceTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = PaylanceTheme.colors.primary)
    }
} 