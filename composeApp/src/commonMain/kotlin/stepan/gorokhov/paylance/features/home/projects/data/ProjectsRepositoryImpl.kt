package stepan.gorokhov.paylance.features.home.projects.data

import stepan.gorokhov.paylance.features.home.projects.data.network.ProjectsApi
import stepan.gorokhov.paylance.features.home.projects.data.network.ResponseApi
import stepan.gorokhov.paylance.features.home.projects.data.network.models.GenerateDescriptionRequest
import stepan.gorokhov.paylance.features.home.projects.data.network.models.ResponseOnProjectRequest
import stepan.gorokhov.paylance.features.home.projects.data.network.toCreateRequest
import stepan.gorokhov.paylance.features.home.projects.data.network.toDomain
import stepan.gorokhov.paylance.features.home.projects.domain.ProjectsRepository
import stepan.gorokhov.paylance.features.home.projects.domain.models.GeneratedDescription
import stepan.gorokhov.paylance.features.home.projects.domain.models.NewProject
import stepan.gorokhov.paylance.features.home.projects.domain.models.Project

class ProjectsRepositoryImpl(
    private val projectsApi: ProjectsApi
) : ProjectsRepository {
    override suspend fun getProjects(offset: Long): Result<List<Project>> {
        return runCatching {
            projectsApi.getProjects(offset).projects.map { it.toDomain() }
        }
    }

    override suspend fun getProjectById(id: String): Result<Project> {
        return runCatching {
            projectsApi.getProject(id).toDomain()
        }
    }

    override suspend fun getClientProjects(offset: Long): Result<List<Project>> {
        return runCatching {
            projectsApi.getClientProjects(offset).projects.map { it.toDomain() }
        }
    }

    override suspend fun getFreelancerProjects(offset: Long): Result<List<Project>> {
        return runCatching {
            projectsApi.getFreelancerProjects(offset).projects.map { it.toDomain() }
        }
    }

    override suspend fun findProjects(text: String, offset: Long): Result<List<Project>> {
        return runCatching {
            projectsApi.findProject(text = text, offset = offset).projects.map { it.toDomain() }
        }
    }

    override suspend fun createProject(project: NewProject): Result<Project> {
        return runCatching {
            projectsApi.createProject(project.toCreateRequest()).toDomain()
        }
    }

    override suspend fun closeProject(id: String): Result<Unit> {
        return runCatching {
            projectsApi.closeProject(id)
        }
    }

    override suspend fun generateProjectDescription(prompt: String): Result<GeneratedDescription> {
        return runCatching {
            val request = GenerateDescriptionRequest(prompt)
            projectsApi.generateDescription(request).toDomain()
        }
    }
}