package com.example.mathapp.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.mathapp.ui.profile.Child
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel: ViewModel() {

    private val mutableState: MutableStateFlow<ProfileState> = MutableStateFlow(
        ProfileState.Success
    )
    val state: StateFlow<ProfileState> = mutableState.asStateFlow()
}
