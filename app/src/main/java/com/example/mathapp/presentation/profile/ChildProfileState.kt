package com.example.mathapp.presentation.profile

import com.example.mathapp.presentation.profile.ProfileState.Success.UserInfo.Child

sealed interface ChildProfileState {

    data object Loading : ChildProfileState

    data object Error : ChildProfileState

    data class Success(
        val child: Child,
    ) : ChildProfileState
}

