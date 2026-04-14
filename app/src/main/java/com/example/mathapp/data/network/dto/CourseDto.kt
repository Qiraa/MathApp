package com.example.mathapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDto(
    @SerialName("id") val id: String,
    @SerialName("name_course") val nameCourse: String,
    @SerialName("description") val description: String? = null,
    @SerialName("min_class") val minClass: Int,
    @SerialName("max_class") val maxClass: Int,
    @SerialName("max_students") val maxStudents: Int,
)
