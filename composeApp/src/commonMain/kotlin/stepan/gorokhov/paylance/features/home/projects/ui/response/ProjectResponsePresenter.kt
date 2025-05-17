package stepan.gorokhov.paylance.features.home.projects.ui.response

interface ProjectResponsePresenter {
    fun loadResponse()
    fun navigateBack()
    fun acceptResponse()
    fun rejectResponse()
}