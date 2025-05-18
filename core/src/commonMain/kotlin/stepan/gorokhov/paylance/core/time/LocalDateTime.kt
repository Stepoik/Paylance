package stepan.gorokhov.paylance.core.time

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.Companion.now(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}

expect fun LocalDateTime.formatHoursMinutes(): String

expect fun LocalDateTime.formatDateHoursMinutes(): String

fun LocalDateTime.fromUTC(): LocalDateTime {
    return toInstant(TimeZone.UTC).toLocalDateTime(TimeZone.currentSystemDefault())
}