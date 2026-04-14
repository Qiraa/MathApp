package com.example.mathapp.domain.journal

sealed interface JournalData {
    data class Student(val journal: Journal) : JournalData
    data class Professor(val professorJournal: ProfessorJournal) : JournalData
}

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

data class ProfessorJournal(val groups: List<JournalGroup>)

data class JournalGroup(
    val courseName: String,
    val dayOfWeek: String,
    val timeSet: String,
    val groupType: String,
    val studentCount: Int,
)
