package stepan.gorokhov.paylance.features.home.tasks.domain

import stepan.gorokhov.paylance.features.home.tasks.domain.models.Task

interface TasksRepository {
    suspend fun getTasks(): Result<List<Task>>

    suspend fun getTaskById(id: String): Result<Task>

    suspend fun findTasks(text: String): Result<List<Task>>

    suspend fun createTask(task: Task): Result<Any?>
}