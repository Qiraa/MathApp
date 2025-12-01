package com.example.mathapp.presentation.profile

import com.example.mathapp.ui.profile.Child

sealed interface ProfileState {

    data class Success(val child: Child): ProfileState

    data object Error: ProfileState

    data object Loading: ProfileState
}
