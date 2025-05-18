package stepan.gorokhov.paylance.features.home.projects.ui.generateDescription

interface GenerateDescriptionPresenter {
    fun setPrompt(prompt: String)
    fun generateDescription()
    fun acceptDescription()
    fun rejectDescription()
    fun navigateBack()
} 