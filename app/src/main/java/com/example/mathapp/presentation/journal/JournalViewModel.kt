package com.example.mathapp.presentation.journal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mathapp.domain.journal.Journal
import com.example.mathapp.domain.journal.JournalClass
import com.example.mathapp.data.journal.JournalRepository
import com.example.mathapp.di.Di
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class JournalViewModel(
    private val journalRepository: JournalRepository = Di.journalRepository,
) : ViewModel() {

    private val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val journal: MutableStateFlow<Journal?> = MutableStateFlow(null)
    private val selectedCourseIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    val state: StateFlow<JournalState> = combine(
        isLoading,
        journal,
        selectedCourseIndex,
    ) { loading, journal, selectedCourseIndex ->
        if (loading) {
            JournalState.Loading
        } else if (journal == null) {
            JournalState.Error
        } else {
            val courses = journal.classesForCourse.keys.toList()
            val selectedCourse = courses[selectedCourseIndex]

            JournalState.Success(
                courses = courses,
                activeCourseIndex = selectedCourseIndex,
                classes = journal.classesForCourse
                    .getValue(selectedCourse)
                    .map { it.toStateJournalClass() },
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), JournalState.Loading)

    init {
        loadJournal()
    }

    fun changeCourse(index: Int) {
        selectedCourseIndex.value = index
    }

    fun openFile(name: String) {
        // TODO open file
    }

    private fun loadJournal() {
        isLoading.value = true
        viewModelScope.launch {
            runCatching {
                journal.value = journalRepository.getJournal(0)
            }
            isLoading.value = false
        }
    }

    private fun JournalClass.toStateJournalClass(): JournalState.Success.JournalClass {
        return JournalState.Success.JournalClass(
            date = date,
            visited = visited,
            score = score,
            homework = homework?.let { homework ->
                JournalState.Success.JournalClass.Homework(
                    description = homework.description,
                    files = homework.files,
                )
            },
        )
    }
}
