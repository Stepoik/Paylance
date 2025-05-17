package stepan.gorokhov.paylance.features.home.projects.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import kotlinx.collections.immutable.ImmutableList

fun LazyListScope.projects(
    projects: ImmutableList<ProjectPreview>,
    onProjectClick: (id: String) -> Unit
) {
    items(projects) { project ->
        ProjectItem(
            project,
            onProjectClick = { onProjectClick(project.id) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ProjectItem(
    project: ProjectPreview,
    onProjectClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.clickable(onClick = onProjectClick)) {
        Row {
            Column {
                Text(
                    project.title,
                    style = PaylanceTheme.typography.titleLarge,
                    color = PaylanceTheme.colors.onBackground,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    project.createdText,
                    style = PaylanceTheme.typography.bodyLarge,
                    color = PaylanceTheme.colors.onBackground
                )
            }
        }
    }
}