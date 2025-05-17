package stepan.gorokhov.paylance.features.home.projects.ui.createProject

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDateTime

@Stable
interface CreateProjectPresenter {
    fun setTitle(title: String)

    fun setDescription(description: String)

    fun setBudget(budget: String)

    fun setDeadline(date: LocalDateTime)

    fun onClickCreate()

    fun addSkill(skill: String)

    fun removeSkill(skill: String)
}