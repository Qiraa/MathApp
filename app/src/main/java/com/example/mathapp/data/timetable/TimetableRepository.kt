package com.example.mathapp.data.timetable

import com.example.mathapp.domain.timetable.Timetable

interface TimetableRepository {

    suspend fun getTimetableForToday(studentId: Int, date: Long): Timetable
}
