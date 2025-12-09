package com.example.mathapp.presentation.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mathapp.data.notification.NotificationRepository
import com.example.mathapp.di.Di
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val notificationRepository: NotificationRepository = Di.notificationRepository,
) : ViewModel() {

    private val mutableState: MutableStateFlow<NotificationState> =
        MutableStateFlow(NotificationState.Loading)
    val state: StateFlow<NotificationState> = mutableState.asStateFlow()

    init {
        loadNotifications()
    }

    fun onRemoveNotification(notificationId: Int) {
        viewModelScope.launch {
            notificationRepository.removeNotification(notificationId)
            loadNotifications()
        }
    }

    private fun loadNotifications() {
        mutableState.value = NotificationState.Loading
        try {
            val notifications = notificationRepository.getNotifications(0)
            val state = NotificationState.Success(
                notifications.map {
                    NotificationState.Success.Notification(it.id, it.title, it.date)
                }
            )
            mutableState.value = state
        } catch (_: Exception) {
            mutableState.value = NotificationState.Error
        }
    }
}
