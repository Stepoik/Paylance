package stepan.gorokhov.paylance.features.home.tasks.ui.main

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import stepan.gorokhov.paylance.features.home.profile.domain.models.User
import stepan.gorokhov.paylance.features.home.tasks.domain.models.Author
import stepan.gorokhov.paylance.features.home.tasks.domain.models.Task

sealed class TasksMainState {
    data object Loading : TasksMainState()
    data class TasksLoaded(
        val user: User,
        val tasks: ImmutableList<TaskPreview> = persistentListOf()
    ) : TasksMainState()

    data object Error : TasksMainState()
}

data class TaskPreview(
    val id: String,
    val title: String,
    val author: Author,
    val createdText: String
)

sealed class TasksMainEffect {
    data object NavigateProfile : TasksMainEffect()
    data class NavigateTask(val id: String) : TasksMainEffect()
}