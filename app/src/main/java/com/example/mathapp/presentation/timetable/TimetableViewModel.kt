package com.example.mathapp.presentation.timetable

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TimetableViewModel: ViewModel() {
    private val mutableState: MutableStateFlow<TimetableState> = MutableStateFlow(
       TimetableState.Success
   )
    val state: StateFlow<TimetableState> = mutableState.asStateFlow()
}
