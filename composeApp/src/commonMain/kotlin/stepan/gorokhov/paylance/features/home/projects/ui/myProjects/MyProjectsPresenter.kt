package stepan.gorokhov.paylance.features.home.projects.ui.myProjects

interface MyProjectsPresenter {
    fun loadProjects()

    fun createProject()

    fun openProject(id: String)
}