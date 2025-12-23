package com.example.mathapp.domain.profile

sealed interface Profile {
    val name: String
    val avatarUrl: String?
    val phone: String

    data class Child(
        override val name: String,
        override val avatarUrl: String?,
        override val phone: String,
        val courses: List<Course>,
    ) : Profile

    data class Parent(
        override val name: String,
        override val avatarUrl: String?,
        override val phone: String,
        val children: List<Child>,
    ) : Profile
}

data class Course(
    val subject: String,
    val time: String,
    val professors: List<String>,
    val address: String,
)
