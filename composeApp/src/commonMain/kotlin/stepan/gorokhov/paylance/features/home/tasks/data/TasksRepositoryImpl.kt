package stepan.gorokhov.paylance.features.home.tasks.data

import stepan.gorokhov.paylance.features.home.tasks.data.network.TasksApi
import stepan.gorokhov.paylance.features.home.tasks.data.network.toCreateRequest
import stepan.gorokhov.paylance.features.home.tasks.data.network.toDomain
import stepan.gorokhov.paylance.features.home.tasks.domain.TasksRepository
import stepan.gorokhov.paylance.features.home.tasks.domain.models.Task

class TasksRepositoryImpl(
    private val tasksApi: TasksApi
) : TasksRepository {
    override suspend fun getTasks(offset: Long): Result<List<Task>> {
        return runCatching {
            tasksApi.getTasks(offset).orders.map { it.toDomain() }
        }
    }

    override suspend fun getTaskById(id: String): Result<Task> {
        return runCatching {
            tasksApi.getTask(id).toDomain()
        }
    }

    override suspend fun findTasks(text: String): Result<List<Task>> {
        return runCatching {
            tasksApi.findTask(text).orders.map { it.toDomain() }
        }
    }

    override suspend fun createTask(task: Task): Result<Any?> {
        return runCatching {
            tasksApi.createTask(task.toCreateRequest())
        }
    }
}