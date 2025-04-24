package gorokhov.stepan.paylance.uikit

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun PaylanceTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides provideColors(isDarkTheme),
        LocalTypography provides typography,
        content = {
            content()
        }
    )
}

@Composable
private fun provideColors(isDarkTheme: Boolean): Colors {
    return lightColors
}

@Composable
private fun provideCalendarColors(isDarkTheme: Boolean): CalendarColors {
    return if (isDarkTheme) {
        darkCalendarColors
    } else {
        lightCalendarColors
    }
}

object PaylanceTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

internal val lightColors = Colors(
    primary = Color(0xFF52AE77),
    onPrimary = Color.White,
    secondary = Color(0xFFA4DD28),
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color(0xFFF3F4F6),
    onSurface = Color(0x801D1B20),
    border = Color(0x801D1B20),
    dialogBackground = Color.White,
    onDialog = Color(0x881B1B1B),
    state = Color(0xFF83B31E),
    tertiary = Color(0xFFA3DD28),
    onTertiary = Color.Black,
    error = Color(0xFFAA0000),
    textError = Color(0xFFE20000),
    hint = Color(0x660A0A0A),
    cardBackground = Color.White,
    primaryContainer = Color(0xFF1B1B1B),
    secondaryCardBackground = Color(0xFF1B1B1B),
    tertiaryCardBackground = Color(0xFFB8B8B8),
    floatingButton = Color(0xFF1B1B1B),
    onPrimaryVariant = Color(0xFF505050),
    secondaryContainer = Color.Black,
)

internal val lightCalendarColors = CalendarColors(
    completedDate = Color(0x80A3DD28),
    futureDate = Color(0x8064CEFF),
    partiallyCompletedDate = Color(0xFFDFDFDF),
    skippedDate = Color(0x80CC2828),
    skippedText = Color(0xFFCC2828),
    futureText = Color(0xFF2899CC),
    completedText = Color(0xFF31AC1E),
    partiallyCompletedText = Color(0xFF797979),
)

internal val darkCalendarColors = CalendarColors(
    completedDate = Color(0xFF6E9715),
    futureDate = Color(0x8064CEFF),
    partiallyCompletedDate = Color(0xFFDFDFDF),
    skippedDate = Color(0x80CC2828),
    skippedText = Color(0xFFF15454),
    futureText = Color(0xFF2899CC),
    completedText = Color(0xFF8CF27C),
    partiallyCompletedText = Color(0xFF929292),
)


internal val typography
    @Composable
    get() = Typography(
        titleLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 43.5.sp
        ),
        titleMedium = TextStyle(
            fontSize = 24.sp,
            lineHeight = 29.sp,
            fontWeight = FontWeight.Medium
        ),
        titleSemiMedium = TextStyle(
            fontSize = 20.sp,
            lineHeight = 24.2.sp,
            fontWeight = FontWeight.Normal
        ),
        titleSmall = TextStyle(
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.SemiBold
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 19.sp
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 17.sp
        ),
        bodySmall = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 14.5.sp
        ),
        bodyExtraSmall = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 13.sp
        ),
        button = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 17.sp
        )
    )

data class Colors(
    val primary: Color,
    val secondary: Color,
    val onPrimary: Color,
    val onPrimaryVariant: Color,
    val onSecondary: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val border: Color,
    val dialogBackground: Color,
    val onDialog: Color,
    val state: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val error: Color,
    val textError: Color,
    val hint: Color,
    val cardBackground: Color,
    val primaryContainer: Color,
    val secondaryCardBackground: Color,
    val tertiaryCardBackground: Color,
    val floatingButton: Color,
    val secondaryContainer: Color
)

data class Typography(
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSemiMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val bodyExtraSmall: TextStyle,
    val button: TextStyle
)

data class CalendarColors(
    val completedDate: Color,
    val skippedDate: Color,
    val futureDate: Color,
    val partiallyCompletedDate: Color,
    val completedText: Color,
    val skippedText: Color,
    val partiallyCompletedText: Color,
    val futureText: Color
)

internal val LocalTypography = staticCompositionLocalOf<Typography> { error("No default values") }
internal val LocalColors = staticCompositionLocalOf<Colors> { error("No default values") }
