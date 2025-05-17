package stepan.gorokhov.paylance.features.home.projects.ui.myProjects

import stepan.gorokhov.paylance.features.home.projects.domain.ProjectsRepository
import stepan.gorokhov.paylance.features.home.projects.domain.models.Project

class FreelancerProjectsViewModel(
    private val projectsRepository: ProjectsRepository
) : AbstractMyProjectsViewModel() {
    override suspend fun getProjects(offset: Long): Result<List<Project>> {
        return projectsRepository.getFreelancerProjects(offset)
    }
}