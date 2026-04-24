package com.example.mathapp.presentation.profile

import com.example.mathapp.presentation.profile.ProfileState.Success.UserInfo.Child

sealed interface ChildProfileState {

    data object Loading : ChildProfileState

    data class Error(
        val reason: ErrorReason,
    ) : ChildProfileState

    data class Success(
        val child: Child,
    ) : ChildProfileState

    enum class ErrorReason {
        NETWORK,
        UNSUPPORTED_PROFILE_TYPE,
        CHILD_NOT_FOUND,
    }
}

