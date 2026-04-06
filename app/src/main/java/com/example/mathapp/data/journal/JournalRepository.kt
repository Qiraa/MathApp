package com.example.mathapp.data.journal

import com.example.mathapp.domain.journal.JournalData

interface JournalRepository {

    suspend fun getJournal(): JournalData
}
