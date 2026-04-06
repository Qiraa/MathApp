package com.example.mathapp.data.profile

import com.example.mathapp.data.auth.SessionManager
import com.example.mathapp.data.network.ApiService
import com.example.mathapp.data.network.dto.UserDto
import com.example.mathapp.domain.profile.Course
import com.example.mathapp.domain.profile.Profile
import com.example.mathapp.domain.profile.ProfessorGroup

class ApiProfileRepository(
    private val apiService: ApiService,
    private val sessionManager: SessionManager,
) : ProfileRepository {

    override suspend fun getProfile(): Profile {
        sessionManager.ensureLoggedIn()

        val username = sessionManager.requireUsername()
        val user = apiService.getUserByUsername(username)

        return when (user.role.orEmpty()) {
            "teacher" -> buildProfessorProfile(user)
            "parent" -> buildParentProfile(user)
            else -> buildChildProfile(user)
        }
    }

    private suspend fun buildProfessorProfile(user: UserDto): Profile.Professor {
        // TODO: professor_id may differ from user_id if the API uses a separate Professor entity
        val groups = apiService.getTeacherGroups(user.id).map { dto ->
            ProfessorGroup(
                courseName = dto.nameCourse,
                dayOfWeek = dto.dayOfWeek,
                timeSet = dto.timeSet,
                groupType = dto.groupType,
                studentCount = dto.studentCount,
            )
        }
        return Profile.Professor(
            name = listOfNotNull(user.name, user.surname, user.patronymic).joinToString(" "),
            avatarUrl = user.avatarUrl,
            phone = user.phone ?: "",
            professorType = "teacher",
            workPlace = "",
            groups = groups,
        )
    }

    private suspend fun buildParentProfile(user: UserDto): Profile.Parent {
        // TODO: replace mock children list when GET /parent/{parent_id}/children schema is documented
        return Profile.Parent(
            name = listOfNotNull(user.name, user.surname, user.patronymic).joinToString(" "),
            avatarUrl = user.avatarUrl,
            phone = user.phone ?: "",
            children = mockChildren(),
        )
    }

    private suspend fun buildChildProfile(user: UserDto): Profile.Child {
        // TODO: filter courses by child's class_number when child entity lookup is available
        val courses = apiService.getAllCourses().map { dto ->
            Course(
                subject = dto.nameCourse,
                time = "",
                // TODO: professors not provided by GET /course/all — see MISMATCHES.md
                professors = emptyList(),
                // TODO: address not provided by GET /course/all — see MISMATCHES.md
                address = "",
            )
        }
        return Profile.Child(
            name = listOfNotNull(user.name, user.surname, user.patronymic).joinToString(" "),
            avatarUrl = user.avatarUrl,
            phone = user.phone ?: "",
            courses = courses,
        )
    }

    // TODO: replace with GET /parent/{parent_id}/children when schema is documented
    private fun mockChildren(): List<Profile.Child> = listOf(
        Profile.Child(
            name = "Виталий Иванович Иванов",
            phone = "+7 908 182-65-45",
            avatarUrl = null,
            courses = listOf(
                Course("Математика", "17:00 - 19:15", listOf("Иван Иванович Иванов"), "4к, ул. Нефтезаводская 11"),
                Course("Шахматы", "19:20 - 20:30", listOf("Петр Петрович Петров"), "4к, ул. Нефтезаводская 11"),
            ),
        ),
        Profile.Child(
            name = "Алина Ивановна Иванова",
            phone = "+7 908 182-65-45",
            avatarUrl = null,
            courses = listOf(
                Course("Математика", "17:00 - 19:15", listOf("Иван Иванович Иванов"), "4к, ул. Нефтезаводская 11"),
                Course("Шахматы", "19:20 - 20:30", listOf("Петр Петрович Петров"), "4к, ул. Нефтезаводская 11"),
            ),
        ),
    )
}
