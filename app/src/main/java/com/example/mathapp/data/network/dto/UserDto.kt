package com.example.mathapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id") val id: String,
    @SerialName("username") val username: String,
    @SerialName("name") val name: String? = null,
    @SerialName("surname") val surname: String? = null,
    @SerialName("patronymic") val patronymic: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("role") val role: String? = null,
    @SerialName("is_active") val isActive: Boolean? = null,
    @SerialName("temp_password") val tempPassword: Boolean? = null,
    @SerialName("address") val address: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    @SerialName("phone") val phone: String? = null,
)
