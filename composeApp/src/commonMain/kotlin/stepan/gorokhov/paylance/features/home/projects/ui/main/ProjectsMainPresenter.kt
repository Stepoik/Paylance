package stepan.gorokhov.paylance.features.home.projects.ui.main

interface ProjectsMainPresenter {
    fun loadProjects(forced: Boolean = false)

    fun onProfileClicked()

    fun onProjectClicked(id: String)

    fun setSearchText(text: String)
}