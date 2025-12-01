package com.example.mathapp.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mathapp.presentation.profile.ProfileViewModel
import androidx.compose.runtime.getValue
import com.example.mathapp.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
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
        is ProfileState.Success -> SuccessContent(
            modifier = modifier,
//          currentState = currentState.child,
            imageId = currentState.child.imageId,
            userName = currentState.child.name,
            //parentName = currentState.child.parentName,
          //  phoneNumber = currentState.child.phoneNumber,
//subjects = currentState.child.subjects,
        )
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    imageId: Int?,
    userName: String,
 //   childrenName: List<String> = emptyList(),
  //  parentName: String,
  //  phoneNumber: String,
  //  subjects: List<String>?,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        if (imageId == null) {
            Image(
                painter = painterResource(R.drawable.profile_without_photo),
                contentDescription = null,
            )
        } else {
            Image(
                painter = painterResource(imageId),
                contentDescription = null,
            )
        }
        Text(
            text = "sddsds",
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}
