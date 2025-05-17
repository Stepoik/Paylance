package stepan.gorokhov.paylance.features.home.projects.ui.main

interface ProjectsMainPresenter {
    fun loadProjects()

    fun onProfileClicked()

    fun onProjectClicked(id: String)
}