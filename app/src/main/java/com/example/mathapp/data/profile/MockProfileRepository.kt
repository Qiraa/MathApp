package com.example.mathapp.data.profile

import com.example.mathapp.domain.profile.Course
import com.example.mathapp.domain.profile.Profile

class MockProfileRepository : ProfileRepository {

    override suspend fun getProfile(): Profile {
        return Profile.Parent(
            name = "Алексей Иванович Иванов",
            phone = "+7 908 182-65-45",
            avatarUrl = null,
            children = listOf(
                createFirstChild(),
                createSecondChild(),
            ),
        )
    }

    private fun createFirstChild(): Profile.Child {
        return Profile.Child(
            name = "Виталий Иванович Иванов",
            phone = "+7 908 182-65-45",
            avatarUrl = null,
            courses = listOf(
                Course(
                    subject = "Математика",
                    time = "17:00 - 19:15",
                    professors = listOf("Иван Иванович Иванов"),
                    address = "4к, ул. Нефтезаводская 11",
                ),
                Course(
                    subject = "Шахматы",
                    time = "19:20 - 20:30",
                    professors = listOf("Петр Петрович Петров"),
                    address = "4к, ул. Нефтезаводская 11",
                )
            ),
        )
    }

    private fun createSecondChild(): Profile.Child {
        return Profile.Child(
            name = "Алина Ивановна Иванова",
            phone = "+7 908 182-65-45",
            avatarUrl = null,
            courses = listOf(
                Course(
                    subject = "Математика",
                    time = "17:00 - 19:15",
                    professors = listOf("Иван Иванович Иванов"),
                    address = "4к, ул. Нефтезаводская 11",
                ),
                Course(
                    subject = "Шахматы",
                    time = "19:20 - 20:30",
                    professors = listOf("Петр Петрович Петров"),
                    address = "4к, ул. Нефтезаводская 11",
                )
            ),
        )
    }
}
