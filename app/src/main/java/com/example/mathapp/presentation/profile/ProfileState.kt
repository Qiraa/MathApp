package com.example.mathapp.presentation.profile

import com.example.mathapp.ui.profile.Child

sealed interface ProfileState {

    data object Success: ProfileState

    data object Error: ProfileState

    data object Loading: ProfileState
}
