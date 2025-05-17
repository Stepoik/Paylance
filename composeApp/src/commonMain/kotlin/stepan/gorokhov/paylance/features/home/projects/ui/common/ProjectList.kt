package stepan.gorokhov.paylance.features.home.projects.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import gorokhov.stepan.paylance.uikit.PaylanceTheme
import kotlinx.collections.immutable.ImmutableList
import stepan.gorokhov.paylance.uikit.components.VerticalSpacer

fun LazyListScope.projects(
    projects: ImmutableList<ProjectPreview>,
    onProjectClick: (id: String) -> Unit
) {
    items(projects) { project ->
        ProjectItem(
            project = project,
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
    Card(
        modifier = modifier
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = PaylanceTheme.colors.surface
        ),
        onClick = onProjectClick,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Заголовок и время создания
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = project.title,
                    style = PaylanceTheme.typography.titleMedium,
                    color = PaylanceTheme.colors.onBackground,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = project.createdText,
                    style = PaylanceTheme.typography.bodySmall,
                    color = PaylanceTheme.colors.onBackground.copy(alpha = 0.6f)
                )
            }
            
            VerticalSpacer(12.dp)
            
            // Информация о заказчике
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    modifier = Modifier.size(32.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = PaylanceTheme.colors.primary.copy(alpha = 0.1f)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = project.author.name.firstOrNull().toString(),
                            style = PaylanceTheme.typography.bodyMedium,
                            color = PaylanceTheme.colors.primary
                        )
                    }
                }
                
                Column {
                    Text(
                        text = project.author.name,
                        style = PaylanceTheme.typography.bodyMedium,
                        color = PaylanceTheme.colors.onBackground
                    )
                    Text(
                        text = project.author.rating.toString(),
                        style = PaylanceTheme.typography.bodySmall,
                        color = PaylanceTheme.colors.onBackground.copy(alpha = 0.6f)
                    )
                }
            }
            
            VerticalSpacer(12.dp)
            
            // Бюджет и навыки
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = project.budget,
                    style = PaylanceTheme.typography.titleMedium,
                    color = PaylanceTheme.colors.primary
                )
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    project.skills.take(3).forEach { skill ->
                        SkillChip(skill = skill)
                    }
                    if (project.skills.size > 3) {
                        Text(
                            text = "+${project.skills.size - 3}",
                            style = PaylanceTheme.typography.bodySmall,
                            color = PaylanceTheme.colors.onBackground.copy(alpha = 0.6f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SkillChip(
    skill: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = PaylanceTheme.colors.primary.copy(alpha = 0.1f),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = skill,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = PaylanceTheme.typography.bodySmall,
            color = PaylanceTheme.colors.primary
        )
    }
}