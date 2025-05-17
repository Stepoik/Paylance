package stepan.gorokhov.paylance.uikit.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import gorokhov.stepan.paylance.uikit.PaylanceTheme

@Composable
fun AreaTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        modifier = modifier.heightIn(min = 150.dp),
        value = value,
        enabled = enabled,
        onValueChange = onValueChanged,
        singleLine = false,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = PaylanceTheme.typography.bodyLarge.copy(color = PaylanceTheme.colors.onSurface),
    ) { innerTextField ->
        Box(
            Modifier.clip(RoundedCornerShape(6.dp))
                .background(PaylanceTheme.colors.surface)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.TopStart
        ) {
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