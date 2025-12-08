package com.example.mathapp.domain

data class Timetable(
    val date: String,
    val items: List<Item>,
) {

    data class Item(
        val subject: String,
        val professorName: String,
        val address: String,
        val startTime: String,
        val endTime: String,
    )
}
