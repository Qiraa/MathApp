package com.example.mathapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonDto(
    @SerialName("group_id") val groupId: String,
    @SerialName("name_course") val nameCourse: String,
    @SerialName("day_of_week") val dayOfWeek: String,
    @SerialName("time_set") val timeSet: String,
    @SerialName("group_type") val groupType: String,
    @SerialName("student_count") val studentCount: Int,
    @SerialName("lesson_date") val lessonDate: String,
    @SerialName("lesson_topic") val lessonTopic: String,
    @SerialName("number_room") val numberRoom: Int,
    @SerialName("room_address") val roomAddress: String,
)
