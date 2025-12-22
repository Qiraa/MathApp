package com.example.mathapp.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mathapp.presentation.profile.ProfileViewModel
import androidx.compose.runtime.getValue
import com.example.mathapp.presentation.profile.ProfileState
import com.example.mathapp.ui.common.ErrorScreen
import com.example.mathapp.ui.common.LoadingScreen

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    when (val currentState = state) {
        ProfileState.Error -> ErrorScreen(modifier = modifier)
        ProfileState.Loading -> LoadingScreen(modifier = modifier)
        is ProfileState.Success -> StudentSuccessContent(
            modifier = modifier,
            imageId = null,
            name = "Ivanov Ivan Ivanovich",
            role = UserRole.STUDENT,
            phone = "89131234567",
            course = listOf(
                Course(
                    subject = "math",
                    time = "15:00 - 17:00",
                    professorName = "Ryabova AA",
                    address = "OmSU",
                ),
                Course(
                    subject = "math",
                    time = "15:00 - 17:00",
                    professorName = "Ryabova AA",
                    address = "OmSU",
                )
            )
        )
    }
}
