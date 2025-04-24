package stepan.gorokhov.paylance.features.home.tasks.data

import stepan.gorokhov.paylance.features.home.tasks.domain.TasksRepository
import stepan.gorokhov.paylance.features.home.tasks.domain.models.Task

class TasksRepositoryImpl : TasksRepository {
    override suspend fun getTasks(): Result<List<Task>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskById(id: String): Result<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun findTasks(text: String): Result<List<Task>> {
        TODO("Not yet implemented")
    }

    override suspend fun createTask(task: Task): Result<Any?> {
        TODO("Not yet implemented")
    }
}