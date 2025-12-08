package com.example.mathapp.presentation.timetable

sealed interface TimetableState {

    data class Success(
        val userName: String,
        val date: String,
        val courses: List<Course>,
        val showDatePicker: Boolean,
    ): TimetableState {

        data class Course(
            val startTime: String,
            val endTime: String,
            val subject: String,
            val professorName: String,
            val address: String,
        )
    }

    data object Error: TimetableState

    data object Loading: TimetableState
}
