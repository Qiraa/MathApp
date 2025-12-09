package com.example.mathapp.data.notification

import com.example.mathapp.domain.notification.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {

    fun haveNotifications(): Flow<Boolean>

    fun getNotifications(studentId: Int): List<Notification>

    suspend fun removeNotification(id: Int)
}
