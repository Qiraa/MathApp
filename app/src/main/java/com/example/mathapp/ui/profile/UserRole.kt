package com.example.mathapp.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mathapp.R

enum class UserRole {
    PARENT,
    STUDENT,
}

@Composable
fun UserRole.toReadableString(): String {
    return when(this) {
        UserRole.STUDENT -> stringResource(R.string.student)
        UserRole.PARENT -> stringResource(R.string.parent)
    }
}
