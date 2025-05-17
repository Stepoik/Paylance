package stepan.gorokhov.paylance.uikit.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gorokhov.stepan.paylance.uikit.PaylanceTheme

@Composable
fun LoadingButton(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.width(IntrinsicSize.Max),
        onClick = {},
        enabled = false,
        colors = CardDefaults.cardColors(containerColor = PaylanceTheme.colors.primary)
    ) {
        Box(
            Modifier.fillMaxWidth().padding(vertical = 9.dp, horizontal = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(Modifier.size(24.dp), color = PaylanceTheme.colors.primary)
        }
    }
}