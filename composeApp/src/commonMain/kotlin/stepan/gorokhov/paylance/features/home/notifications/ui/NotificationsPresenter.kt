package stepan.gorokhov.paylance.features.home.notifications.ui

interface NotificationsPresenter {
    fun loadNotifications(forced: Boolean = false)
    fun navigateBack()
    fun onNotificationClick(notification: NotificationVO)
}