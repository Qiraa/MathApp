package com.example.mathapp.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.mathapp.ui.profile.Child
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel: ViewModel() {

    private val mutableState: MutableStateFlow<ProfileState> = MutableStateFlow(
        ProfileState.Error
//        ProfileState.Success(
//            Child(
//                imageId = null,
//                name = "Иванов Иван",
//                parentName = "Иванова Анна",
//                phoneNumber = "799912345678",
//                subjects = List(2) {
//                    "Информатика"; "Математика"
//                }
//            )
//        )
    )
    val state: StateFlow<ProfileState> = mutableState.asStateFlow()
}
