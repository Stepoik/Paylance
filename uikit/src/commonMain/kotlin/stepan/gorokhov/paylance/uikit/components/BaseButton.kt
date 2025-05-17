package stepan.gorokhov.paylance.uikit.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gorokhov.stepan.paylance.uikit.PaylanceTheme


@Composable
fun BaseButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.width(IntrinsicSize.Max),
        onClick = onClick,
        enabled = enabled,
        colors = CardDefaults.cardColors(containerColor = PaylanceTheme.colors.primary)
    ) {
        Box(
            Modifier.fillMaxWidth().padding(vertical = 9.dp, horizontal = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text,
                style = PaylanceTheme.typography.bodyLarge,
                color = PaylanceTheme.colors.onPrimary,
                maxLines = 1
            )
        }
    }
}