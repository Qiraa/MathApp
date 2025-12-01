package com.example.mathapp.presentation.timetable

sealed interface TimetableState {

    data object Success: TimetableState

    data object Error: TimetableState

    data object Loading: TimetableState
}
