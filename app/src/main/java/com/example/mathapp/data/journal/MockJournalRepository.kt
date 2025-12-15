package com.example.mathapp.data.journal

import com.example.mathapp.domain.journal.Journal
import com.example.mathapp.domain.journal.JournalClass

class MockJournalRepository : JournalRepository {

    private val mocks: Journal = Journal(
        classesForCourse = mapOf(
            "Математика" to listOf(
                JournalClass(
                    date = "15.12.2025",
                    visited = true,
                    score = 5,
                    homework = JournalClass.Homework(
                        description = "Решить задачу",
                        files = listOf("task.pdf")
                    )
                ),
                JournalClass(
                    date = "16.12.2025",
                    visited = false,
                    score = null,
                    homework = JournalClass.Homework(
                        description = "Повторить материал",
                        files = emptyList()
                    )
                ),
                JournalClass(
                    date = "16.12.2025",
                    visited = true,
                    score = 4,
                    homework = JournalClass.Homework(
                        description = "Прочитать главу",
                        files = listOf("book.pdf")
                    )
                ),
            ),
            "Информатика" to listOf(
                JournalClass(
                    date = "15.12.2025",
                    visited = false,
                    score = null,
                    homework = JournalClass.Homework(
                        description = "Дописать программу",
                        files = listOf("program.pascal")
                    )
                ),
                JournalClass(
                    date = "16.12.2025",
                    visited = true,
                    score = 5,
                    homework = JournalClass.Homework(
                        description = "Повторить материал",
                        files = emptyList()
                    )
                ),
            )
        ),
    )


    override suspend fun getJournal(studentId: Int): Journal = mocks
}
