package stepan.gorokhov.paylance.features.home.notifications.ui

import stepan.gorokhov.paylance.coreui.models.ErrorMessage

sealed interface NotificationsState {
    data object Loading : NotificationsState
    data class Error(val error: ErrorMessage) : NotificationsState
    data class NotificationsLoaded(
        val isLoading: Boolean,
        val notifications: List<NotificationVO>
    ) : NotificationsState
}

sealed interface NotificationsEffect {
    data object NavigateBack : NotificationsEffect
    data class NavigateToProject(val projectId: String) : NotificationsEffect
    data class NavigateToResponse(val responseId: String) : NotificationsEffect
}