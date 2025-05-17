package stepan.gorokhov.paylance.features.home.notifications.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.paylance.coreui.models.ErrorMessage
import stepan.gorokhov.paylance.features.home.notifications.domain.NotificationsRepository
import stepan.gorokhov.paylance.features.home.notifications.domain.models.NotificationType
import stepan.gorokhov.viboranet.core.flow.mapState

class NotificationsViewModel(
    private val repository: NotificationsRepository
) : ViewModel(), NotificationsPresenter {
    private val _state = MutableStateFlow(NotificationViewModelState())
    val state = _state.mapState { it.toScreenState() }

    private val _effect = MutableSharedFlow<NotificationsEffect>(extraBufferCapacity = 1)
    val effect: SharedFlow<NotificationsEffect> = _effect.asSharedFlow()

    override fun loadNotifications(forced: Boolean) {
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.Default) {
            val offset = if (forced) {
                0
            } else _state.value.notifications.size
            repository.getNotifications(offset.toLong()).onSuccess { notifications ->
                val notificationVOs = notifications.map { it.toVO() }
                _state.update {
                    val newNotifications = if (forced) {
                        notificationVOs.toImmutableList()
                    } else {
                        (it.notifications + notificationVOs).toImmutableList()
                    }
                    it.copy(notifications = newNotifications)
                }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    override fun navigateBack() {
        _effect.tryEmit(NotificationsEffect.NavigateBack)
    }

    override fun onNotificationClick(notification: NotificationVO) {
        viewModelScope.launch(Dispatchers.Default) {
            if (notification.type == NotificationType.RESPONSE_RESULT) {
                _effect.emit(NotificationsEffect.NavigateToProject(notification.projectId))
            } else {
                _effect.emit(NotificationsEffect.NavigateToResponse(notification.responseId))
            }
        }
    }
}

private data class NotificationViewModelState(
    val isLoading: Boolean = false,
    val notifications: ImmutableList<NotificationVO> = persistentListOf(),
    val error: ErrorMessage? = null
) {
    fun toScreenState(): NotificationsState {
        return when {
            isLoading && notifications.isEmpty() -> NotificationsState.Loading
            error != null && notifications.isEmpty() -> NotificationsState.Error(error)
            else -> NotificationsState.NotificationsLoaded(
                isLoading = isLoading,
                notifications = notifications
            )
        }
    }
}