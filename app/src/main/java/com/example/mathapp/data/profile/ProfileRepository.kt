package com.example.mathapp.data.profile

import com.example.mathapp.domain.profile.Profile

interface ProfileRepository {

    suspend fun getProfile(): Profile
}
