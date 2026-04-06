package com.example.mathapp.presentation.journal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mathapp.data.journal.JournalRepository
import com.example.mathapp.domain.journal.JournalClass
import com.example.mathapp.domain.journal.JournalData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JournalViewModel(
    private val journalRepository: JournalRepository,
) : ViewModel() {

    private val mutableState: MutableStateFlow<JournalState> = MutableStateFlow(JournalState.Loading)
    val state: StateFlow<JournalState> = mutableState.asStateFlow()

    private var studentData: JournalData.Student? = null
    private var selectedCourseIndex: Int = 0

    init {
        loadJournal()
    }

    fun changeCourse(index: Int) {
        val data = studentData ?: return
        selectedCourseIndex = index
        mutableState.value = data.toSuccessState(index)
    }

    fun openFile(name: String) {
        // TODO open file
    }

    private fun loadJournal() {
        mutableState.value = JournalState.Loading
        viewModelScope.launch {
            runCatching { journalRepository.getJournal() }
                .onSuccess { data ->
                    when (data) {
                        is JournalData.Professor -> {
                            mutableState.value = JournalState.ProfessorSuccess(
                                groups = data.professorJournal.groups.map { group ->
                                    JournalState.ProfessorSuccess.Group(
                                        courseName = group.courseName,
                                        dayOfWeek = group.dayOfWeek,
                                        timeSet = group.timeSet,
                                        groupType = group.groupType,
                                        studentCount = group.studentCount,
                                    )
                                }
                            )
                        }
                        is JournalData.Student -> {
                            studentData = data
                            mutableState.value = data.toSuccessState(selectedCourseIndex)
                        }
                    }
                }
                .onFailure { mutableState.value = JournalState.Error }
        }
    }

    private fun JournalData.Student.toSuccessState(courseIndex: Int): JournalState.StudentSuccess {
        val courses = journal.classesForCourse.keys.toList()
        val safeIndex = courseIndex.coerceIn(0, (courses.size - 1).coerceAtLeast(0))
        val selectedCourse = courses[safeIndex]
        return JournalState.StudentSuccess(
            courses = courses,
            activeCourseIndex = safeIndex,
            classes = journal.classesForCourse.getValue(selectedCourse).map { it.toStateClass() },
        )
    }

    private fun JournalClass.toStateClass(): JournalState.StudentSuccess.JournalClass {
        return JournalState.StudentSuccess.JournalClass(
            date = date,
            visited = visited,
            score = score,
            homework = homework?.let {
                JournalState.StudentSuccess.JournalClass.Homework(
                    description = it.description,
                    files = it.files,
                )
            },
        )
    }
}
