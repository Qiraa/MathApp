package com.example.mathapp.presentation.journal

sealed interface JournalState {

    data class StudentSuccess(
        val courses: List<String>,
        val activeCourseIndex: Int,
        val classes: List<JournalClass>,
    ) : JournalState {

        data class JournalClass(
            val date: String,
            val visited: Boolean,
            val score: Int?,
            val homework: Homework?,
        ) {

            data class Homework(
                val description: String,
                val files: List<String>,
            )
        }
    }

    data class ProfessorSuccess(
        val groups: List<Group>,
    ) : JournalState {

        data class Group(
            val courseName: String,
            val dayOfWeek: String,
            val timeSet: String,
            val groupType: String,
            val studentCount: Int,
        )
    }

    data object Error : JournalState

    data object Loading : JournalState
}
