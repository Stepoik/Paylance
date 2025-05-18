package stepan.gorokhov.paylance.core.time

import android.annotation.SuppressLint
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("ConstantLocale")
private val hoursMinutesFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

@SuppressLint("ConstantLocale")
private val fullDateTimeFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.getDefault())

actual fun LocalDateTime.formatHoursMinutes(): String {
    return hoursMinutesFormatter.format(this.toJavaLocalDateTime())
}

actual fun LocalDateTime.formatDateHoursMinutes(): String {
    return fullDateTimeFormatter.format(this.toJavaLocalDateTime())
}