package com.example.mathapp.data

import com.example.mathapp.domain.Timetable

interface TimetableRepository {

    suspend fun getTimetableForToday(studentId: Int, date: Long): Timetable
}
