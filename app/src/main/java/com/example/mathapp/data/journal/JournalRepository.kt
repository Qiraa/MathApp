package com.example.mathapp.data.journal

import com.example.mathapp.domain.journal.Journal

interface JournalRepository {

    suspend fun getJournal(studentId: Int): Journal
}
