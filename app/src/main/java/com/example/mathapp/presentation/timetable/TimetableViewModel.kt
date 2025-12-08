package com.example.mathapp.presentation.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mathapp.data.MockTimetableRepository
import com.example.mathapp.data.TimetableRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.sql.Date

class TimetableViewModel(
    private val timetableRepository: TimetableRepository = MockTimetableRepository(),
) : ViewModel() {

    private val mutableEffect: MutableSharedFlow<TimetableEffect> = MutableSharedFlow()
    val effect: SharedFlow<TimetableEffect> = mutableEffect.asSharedFlow()

    private val mutableState: MutableStateFlow<TimetableState> =
        MutableStateFlow(TimetableState.Loading)
    val state: StateFlow<TimetableState> = mutableState.asStateFlow()

    init {
        loadTimetable(System.currentTimeMillis())
    }

    fun showDatePicker() {
        val currentState = state.value as? TimetableState.Success ?: return
        mutableState.value = currentState.copy(showDatePicker = true)
    }

    fun hideDatePicker() {
        val currentState = state.value as? TimetableState.Success ?: return
        mutableState.value = currentState.copy(showDatePicker = false)
    }

    fun updateDate(date: Long?) {
        loadTimetable(date ?: System.currentTimeMillis())
    }

    fun openAddress(address: String) {
        viewModelScope.launch {
            mutableEffect.emit(
                TimetableEffect.OpenViewIntent("https://yandex.ru/maps/66/omsk/search/$address")
            )
        }
    }

    private fun loadTimetable(date: Long) {
        viewModelScope.launch {
            val timetable = timetableRepository.getTimetableForToday(0, date)

            mutableState.value = TimetableState.Success(
                userName = "Екатерина",
                date = timetable.date,
                courses = timetable.items.map { item ->
                    TimetableState.Success.Course(
                        startTime = item.startTime,
                        endTime = item.endTime,
                        subject = item.subject,
                        professorName = item.professorName,
                        address = item.address,
                    )
                },
                showDatePicker = false,
            )
        }
    }
}
