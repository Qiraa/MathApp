package com.example.mathapp.di

import com.example.mathapp.data.notification.MockNotificationRepository
import com.example.mathapp.data.notification.NotificationRepository
import com.example.mathapp.data.timetable.MockTimetableRepository
import com.example.mathapp.data.timetable.TimetableRepository

object Di {
    val notificationRepository: NotificationRepository = MockNotificationRepository()
    val timetableRepository: TimetableRepository = MockTimetableRepository()
}
