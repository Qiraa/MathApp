package com.example.mathapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mathapp.data.profile.ProfileRepository
import com.example.mathapp.domain.profile.Profile
import com.example.mathapp.presentation.profile.ChildProfileState.ErrorReason
import com.example.mathapp.presentation.profile.ProfileState.Success.Course
import com.example.mathapp.presentation.profile.ProfileState.Success.UserInfo.Child
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChildProfileViewModel(
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<ChildProfileState>(ChildProfileState.Loading)
    val state: StateFlow<ChildProfileState> = _state

    init {
        loadProfile()
    }

    fun logOut() {
        // TODO: реализовать выход из аккаунта
    }

    private fun loadProfile() {
        viewModelScope.launch {
            val profile = try {
                profileRepository.getProfile()
            } catch (_: Exception) {
                _state.value = ChildProfileState.Error(ErrorReason.NETWORK)
                return@launch
            }

            val child = when (profile) {
                is Profile.Child -> profile
                is Profile.Parent -> profile.children.firstOrNull()
                is Profile.Professor -> {
                    _state.value = ChildProfileState.Error(ErrorReason.UNSUPPORTED_PROFILE_TYPE)
                    return@launch
                }
            }

            if (child == null) {
                _state.value = ChildProfileState.Error(ErrorReason.CHILD_NOT_FOUND)
                return@launch
            }

            _state.value = ChildProfileState.Success(
                child = Child(
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
            )
        }
    }
}