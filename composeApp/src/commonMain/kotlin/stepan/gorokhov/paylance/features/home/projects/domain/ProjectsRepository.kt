package stepan.gorokhov.paylance.features.home.projects.domain

import stepan.gorokhov.paylance.features.home.projects.domain.models.GeneratedDescription
import stepan.gorokhov.paylance.features.home.projects.domain.models.NewProject
import stepan.gorokhov.paylance.features.home.projects.domain.models.Project

interface ProjectsRepository {
    suspend fun getProjects(offset: Long): Result<List<Project>>

    suspend fun getProjectById(id: String): Result<Project>

    suspend fun getClientProjects(offset: Long): Result<List<Project>>

    suspend fun getFreelancerProjects(offset: Long): Result<List<Project>>

    suspend fun findProjects(text: String, offset: Long): Result<List<Project>>

    suspend fun createProject(project: NewProject): Result<Project>

    suspend fun closeProject(id: String): Result<Unit>

    suspend fun generateProjectDescription(prompt: String): Result<GeneratedDescription>
}