package com.example.mathapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mathapp.data.profile.ProfileRepository
import com.example.mathapp.di.Di
import com.example.mathapp.domain.profile.Profile
import com.example.mathapp.presentation.profile.ProfileState.Success.Course
import com.example.mathapp.presentation.profile.ProfileState.Success.UserInfo.Child
import com.example.mathapp.presentation.profile.ProfileState.Success.UserInfo.Parent
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(
    private val profileRepository: ProfileRepository = Di.profileRepository,
) : ViewModel() {

    val state: StateFlow<ProfileState> = flow { emit(profileRepository.getProfile()) }
        .map(::createState)
        .stateIn(viewModelScope, SharingStarted.Lazily, ProfileState.Loading)

    fun logOut() {

    }

    private fun createState(profile: Profile): ProfileState {
        return when (profile) {
            is Profile.Child -> ProfileState.Success(
                userInfo = Child(
                    name = profile.name,
                    avatarUrl = profile.avatarUrl,
                    phone = profile.phone,
                    courses = profile.courses.map { course ->
                        Course(
                            subject = course.subject,
                            time = course.time,
                            professors = course.professors,
                            address = course.address,
                        )
                    },
                )
            )
            is Profile.Parent -> ProfileState.Success(
                userInfo = Parent(
                    name = profile.name,
                    avatarUrl = profile.avatarUrl,
                    phone = profile.phone,
                    children = profile.children.map { child ->
                        Child(
                            name = child.name,
                            avatarUrl = child.avatarUrl,
                            phone = child.phone,
                            courses = child.courses.map { course ->
                                Course(
                                    subject = course.subject,
                                    time = course.time,
                                    professors = course.professors,
                                    address = course.address,
                                )
                            },
                        )
                    },
                )
            )
        }
    }
}
