package stepan.gorokhov.paylance.features.home.tasks.ui.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import kotlinx.collections.immutable.ImmutableList
import stepan.gorokhov.paylance.features.home.tasks.domain.models.Author
import stepan.gorokhov.paylance.features.home.tasks.domain.models.Task
import stepan.gorokhov.paylance.features.home.tasks.ui.main.TaskPreview

fun LazyListScope.tasks(tasks: ImmutableList<TaskPreview>) {
    items(tasks) {

    }
}

@Composable
fun TaskItem(task: TaskPreview, modifier: Modifier = Modifier) {
    Column(modifier) {
        Row {
            Column {
                Text(
                    task.title,
                    style = PaylanceTheme.typography.titleLarge,
                    color = PaylanceTheme.colors.onBackground,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    task.createdText,
                    style = PaylanceTheme.typography.bodyLarge,
                    color = PaylanceTheme.colors.onBackground
                )
            }
        }
    }
}

@Composable
fun AuthorInfo(author: Author, modifier: Modifier = Modifier) {
    Async
}