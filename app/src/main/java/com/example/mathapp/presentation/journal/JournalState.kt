package com.example.mathapp.presentation.journal

sealed interface JournalState {

    data class Success(
        val courses: List<String>,
        val activeCourseIndex: Int,
        val classes: List<JournalClass>,
    ): JournalState {

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

    data object Error: JournalState

    data object Loading: JournalState
}
