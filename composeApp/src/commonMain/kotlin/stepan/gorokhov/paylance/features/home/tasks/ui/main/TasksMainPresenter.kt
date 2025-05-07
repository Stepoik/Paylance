package stepan.gorokhov.paylance.features.home.tasks.ui.main

interface TasksMainPresenter {
    fun loadTasks()

    fun onProfileClicked()

    fun onTaskClicked(id: String)
}