package com.example.mathapp.data.notification

import com.example.mathapp.domain.notification.Notification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class MockNotificationRepository : NotificationRepository {

    private val notifications: MutableStateFlow<List<Notification>> = MutableStateFlow(
        listOf(
            Notification(
                id = 0,
                title = "Занятие по математике отменено",
                date = "22 декабря 2025 в 19:00"
            ),
            Notification(
                id = 1,
                title = "Занятие по информатике отменено",
                date = "19 декабря 2025 в 14:00"
            ),
            Notification(
                id = 2,
                title = "Занятие по шахматам отменено",
                date = "24 декабря 2025 в 11:00"
            ),
            Notification(
                id = 3,
                title = "Занятие по математике отменено",
                date = "18 декабря 2025 в 10:00"
            )
        )
    )

    override fun haveNotifications(): Flow<Boolean> = notifications.map { it.isNotEmpty() }

    override fun getNotifications(studentId: Int): List<Notification> {
        return notifications.value
    }

    override suspend fun removeNotification(id: Int) {
        notifications.value = notifications.value.filter { it.id != id }
    }
}
