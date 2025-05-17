package stepan.gorokhov.paylance.features.home.projects.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.paylance.features.home.projects.data.ProjectsRepositoryImpl
import stepan.gorokhov.paylance.features.home.projects.data.network.ProjectsApi
import stepan.gorokhov.paylance.features.home.projects.domain.ProjectsRepository
import stepan.gorokhov.paylance.features.home.projects.ui.createProject.CreateProjectViewModel
import stepan.gorokhov.paylance.features.home.projects.ui.detail.ProjectDetailsViewModel
import stepan.gorokhov.paylance.features.home.projects.ui.main.ProjectsMainViewModel
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.ClientProjectsViewModel
import stepan.gorokhov.paylance.features.home.projects.ui.myProjects.FreelancerProjectsViewModel
import stepan.gorokhov.paylance.network.AUTHORIZED_HTTP_CLIENT

val projectModule = module {
    single { ProjectsApi(get(qualifier = AUTHORIZED_HTTP_CLIENT)) }
    single<ProjectsRepository> { ProjectsRepositoryImpl(get()) }

    viewModel { ProjectsMainViewModel(get(), get()) }
    viewModel { ClientProjectsViewModel(get()) }
    viewModel { FreelancerProjectsViewModel(get()) }
    viewModel { CreateProjectViewModel(get()) }
    viewModel { params -> ProjectDetailsViewModel(params.get(), get()) }
}