package com.example.mathapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupDto(
    @SerialName("group_id") val groupId: String,
    @SerialName("name_course") val nameCourse: String,
    @SerialName("day_of_week") val dayOfWeek: String,
    @SerialName("time_set") val timeSet: String,
    @SerialName("group_type") val groupType: String,
    @SerialName("student_count") val studentCount: Int,
)
