package com.example.mathapp.presentation.timetable

sealed interface TimetableEffect {

    data class OpenViewIntent(val url: String): TimetableEffect
}
