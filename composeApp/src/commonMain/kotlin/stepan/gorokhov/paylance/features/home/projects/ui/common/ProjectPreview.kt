package stepan.gorokhov.paylance.features.home.projects.ui.common

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.jetbrains.compose.resources.getPluralString
import org.jetbrains.compose.resources.getString
import paylance.composeapp.generated.resources.Res
import paylance.composeapp.generated.resources.days
import paylance.composeapp.generated.resources.hours
import paylance.composeapp.generated.resources.less_than_hour
import paylance.composeapp.generated.resources.more_than_month
import stepan.gorokhov.paylance.core.time.now
import stepan.gorokhov.paylance.features.home.projects.domain.models.Author
import stepan.gorokhov.paylance.features.home.projects.domain.models.Project

data class ProjectPreview(
    val id: String,
    val title: String,
    val author: Author,
    val createdText: String
)

suspend fun Project.toPreview(): ProjectPreview {
    val currentTime = LocalDateTime.now().toInstant(TimeZone.UTC)
    val createdAtInstant = createdAt.toInstant(TimeZone.UTC)
    val diff = currentTime - createdAtInstant
    val createdText = when {
        diff.inWholeDays > 30 -> getString(Res.string.more_than_month)
        diff.inWholeDays > 1 -> getPluralString(
            Res.plurals.days,
            diff.inWholeDays.toInt(),
            diff.inWholeDays
        )

        diff.inWholeHours > 1 -> getPluralString(
            Res.plurals.hours,
            diff.inWholeHours.toInt(),
            diff.inWholeHours
        )

        else -> getString(Res.string.less_than_hour)
    }
    return ProjectPreview(
        id = id,
        title = title,
        author = author,
        createdText = createdText
    )
}