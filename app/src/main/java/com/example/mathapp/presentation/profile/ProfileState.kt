package com.example.mathapp.presentation.profile

sealed interface ProfileState {

    data class Success(
        val userInfo: UserInfo,
    ): ProfileState {

        sealed interface UserInfo {

            val name: String
            val avatarUrl: String?
            val phone: String

            data class Child(
                override val name: String,
                override val avatarUrl: String?,
                override val phone: String,
                val courses: List<Course>,
            ): UserInfo

            data class Parent(
                override val name: String,
                override val avatarUrl: String?,
                override val phone: String,
                val children: List<Child>,
            ): UserInfo
        }

        data class Course(
            val subject: String,
            val time: String,
            val professors: List<String>,
            val address: String,
        )
    }

    data object Error: ProfileState

    data object Loading: ProfileState
}
