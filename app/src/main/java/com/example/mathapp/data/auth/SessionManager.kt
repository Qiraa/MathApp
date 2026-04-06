package com.example.mathapp.data.auth

import com.example.mathapp.data.network.ApiService
import com.example.mathapp.data.network.dto.AuthRequest

class SessionManager(private val apiService: ApiService) {

    private data class Session(
        val accessToken: String,
        val userId: String,
        val username: String,
        val userRole: String,
        val fullName: String,
    )

    private var session: Session? = null

    // Exposed separately so the AuthInterceptor can read it before the full session is built
    private var _accessToken: String? = null
    val accessToken: String? get() = _accessToken

    // TODO: replace with real login screen when auth is fully implemented
    suspend fun ensureLoggedIn() {
        if (session != null) return
        val response = apiService.login(AuthRequest(username = "tester1", password = "Im_testing_TS"))
        // Store token immediately so the next authenticated request (getUserByUsername) includes it
        _accessToken = response.accessToken
        val user = apiService.getUserByUsername("tester1")
        session = Session(
            accessToken = response.accessToken,
            userId = user.id,
            username = user.username,
            userRole = user.role.orEmpty(),
            fullName = listOfNotNull(user.name, user.surname, user.patronymic).joinToString(" "),
        )
    }

    fun requireUserId(): String = session?.userId ?: error("Not logged in")
    fun requireUsername(): String = session?.username ?: error("Not logged in")
    fun requireUserRole(): String = session?.userRole ?: error("Not logged in")
    fun getFullName(): String = session?.fullName.orEmpty()

    fun clear() {
        _accessToken = null
        session = null
    }
}
