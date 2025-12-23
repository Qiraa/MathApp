package com.example.mathapp.data.timetable

import com.example.mathapp.domain.timetable.Timetable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MockTimetableRepository : TimetableRepository {

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale("RU"))

    private val mocks: Map<String, List<Timetable.Item>> = mapOf(
        "24.12.2025" to listOf(
            Timetable.Item(
                "Математика",
                "Рябова Алевтина Витальевна",
                "4 к, ул. Нефтезаводская 11, ауд. 201",
                "14:30",
                "16:00",
            ),
            Timetable.Item(
                "Информатика",
                "Рябова Алевтина Витальевна",
                "4 к, ул. Нефтезаводская 11, ауд. 201",
                "16:05",
                "17:00",
            ),
            Timetable.Item(
                "Шахматы",
                "Рябова Алевтина Витальевна",
                "4 к, ул. Нефтезаводская 11, ауд. 201",
                "17:30",
                "18:00",
            )
        ),
        "25.12.2025" to listOf(
            Timetable.Item(
                "Математика",
                "Рябова Алевтина Витальевна",
                "4 к, ул. Нефтезаводская 11, ауд. 201",
                "14:30",
                "16:00",
            ),
            Timetable.Item(
                "Информатика",
                "Рябова Алевтина Витальевна",
                "4 к, ул. Нефтезаводская 11, ауд. 201",
                "16:05",
                "17:00",
            ),
            Timetable.Item(
                "Шахматы",
                "Рябова Алевтина Витальевна",
                "4 к, ул. Нефтезаводская 11, ауд. 201",
                "17:30",
                "18:00",
            )
        )
    )

    override suspend fun getTimetableForToday(studentId: Int, date: Long): Timetable {
        val calendar = Calendar.getInstance()
            .apply {
                time = Date(date)
                set(Calendar.HOUR, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
        val formatted = dateFormat.format(calendar.time)
        return mocks[formatted]
            ?.let { items -> Timetable(formatted, items) }
            ?: Timetable(formatted, emptyList())
    }
}
