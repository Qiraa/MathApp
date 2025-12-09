package com.example.mathapp.presentation.notification

sealed interface NotificationState {

    data class Success(
        val notifications: List<Notification>,
    ): NotificationState {

        data class Notification(
            val id: Int,
            val title: String,
            val date: String,
        )
    }

    data object Error: NotificationState

    data object Loading: NotificationState
}
