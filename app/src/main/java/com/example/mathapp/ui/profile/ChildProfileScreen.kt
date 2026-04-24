package com.example.mathapp.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mathapp.presentation.profile.ChildProfileState
import com.example.mathapp.presentation.profile.ChildProfileViewModel
import com.example.mathapp.presentation.profile.ProfileState
import com.example.mathapp.ui.common.ErrorScreen
import com.example.mathapp.ui.common.LoadingScreen

@Composable
fun ChildProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ChildProfileViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()
    when (val currentState = state) {
        ChildProfileState.Error -> ErrorScreen(modifier = modifier)
        ChildProfileState.Loading -> LoadingScreen(modifier = modifier)
        is ChildProfileState.Success -> SuccessContent(
            modifier = modifier,
            state = currentState,
            onLogOut = viewModel::logOut,
        )
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    state: ChildProfileState.Success,
    onLogOut: () -> Unit,
) {
    val child = state.child
    ProfileContainer(
        modifier = modifier,
        name = child.name,
        role = "Ученик",
        onDeleteAccount = { },
        onLogOut = onLogOut,
    ) {
        ProfileChildContent(
            child = ProfileState.Success.UserInfo.Child(
                name = child.name,
                avatarUrl = child.avatarUrl,
                phone = child.phone,
                courses = child.courses,
            ),
            isMain = true,
        )
    }
}

