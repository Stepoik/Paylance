package stepan.gorokhov.paylance.uikit.components.textfields

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gorokhov.stepan.paylance.uikit.PaylanceTheme

@Composable
fun BaseOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    singleLine: Boolean = true,
    isError: Boolean = false,
    label: String? = null,
    enabled: Boolean = true,
    minLines: Int = 0,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label?.let { { Text(label, color = PaylanceTheme.colors.primary) } },
        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PaylanceTheme.colors.primary),
        supportingText = supportingText?.let {
            {
                Text(supportingText)
            }
        },
        singleLine = singleLine,
        enabled = enabled,
        isError = isError,
        minLines = minLines,
        modifier = modifier
    )
}