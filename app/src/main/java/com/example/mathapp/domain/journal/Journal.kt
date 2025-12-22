package com.example.mathapp.domain.journal

data class Journal(
    val classesForCourse: Map<String, List<JournalClass>>,
)

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
