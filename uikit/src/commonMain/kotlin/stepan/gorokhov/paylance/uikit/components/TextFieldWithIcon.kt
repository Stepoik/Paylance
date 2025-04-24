package stepan.gorokhov.paylance.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import gorokhov.stepan.paylance.uikit.PaylanceTheme

@Composable
fun TextFieldWithIcon(
    icon: ImageVector,
    value: String,
    onValueChanged: (String) -> Unit,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        enabled = enabled,
        onValueChange = onValueChanged,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = PaylanceTheme.typography.bodyLarge.copy(color = PaylanceTheme.colors.onSurface),
    ) { innerTextField ->
        Row(
            Modifier.clip(RoundedCornerShape(6.dp))
                .background(PaylanceTheme.colors.surface)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(vertical = 12.dp).size(20.dp)
            )
            Box {
                if (value.isEmpty()) {
                    Text(
                        placeholder,
                        color = PaylanceTheme.colors.onSurface,
                        style = PaylanceTheme.typography.bodyLarge,
                        maxLines = 1
                    )
                }
                innerTextField()
            }
        }
    }
}