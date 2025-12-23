package com.example.mathapp.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mathapp.R
import com.example.mathapp.presentation.profile.ProfileState
import com.example.mathapp.presentation.profile.ProfileState.Success.UserInfo
import com.example.mathapp.presentation.profile.ProfileState.Success.UserInfo.Child
import com.example.mathapp.presentation.profile.ProfileState.Success.UserInfo.Parent
import com.example.mathapp.presentation.profile.ProfileViewModel
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
            userInfo = currentState.userInfo,
            onLogOut = viewModel::logOut,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    userInfo: UserInfo,
    onLogOut: () -> Unit,
) {
    var isConfirmationDialogShown by remember { mutableStateOf(false) }

    if (isConfirmationDialogShown) {
        AlertDialog(
            onDismissRequest = { isConfirmationDialogShown = false },
            title = { Text(stringResource(R.string.delete_account_title)) },
            text = { Text(stringResource(R.string.delete_account_text)) },
            confirmButton = {
                TextButton(onClick = { isConfirmationDialogShown = false }) {
                    Text(stringResource(R.string.delete_account_button_text))
                }
            },
        )
    }

    when (userInfo) {
        is Child -> ProfileContainer(
            modifier = modifier,
            name = userInfo.name,
            role = stringResource(R.string.student),
            onDeleteAccount = { isConfirmationDialogShown = true },
            onLogOut = onLogOut,
        ) {
            ProfileChildContent(child = userInfo, isMain = false)
        }

        is Parent -> ProfileContainer(
            modifier = modifier,
            name = userInfo.name,
            role = stringResource(R.string.parent),
            onDeleteAccount = { isConfirmationDialogShown = true },
            onLogOut = onLogOut,
        ) {
            ProfileParentContent(parent = userInfo)
        }
    }
}

@Composable
private fun ProfileContainer(
    modifier: Modifier = Modifier,
    name: String,
    role: String,
    onDeleteAccount: () -> Unit,
    onLogOut: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.profile_title),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = onLogOut,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_logout),
                    contentDescription = null,
                )
            }
        }
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.profile_without_photo),
                contentDescription = null,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(50.dp))
                    .size(55.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(50.dp)
                    )
            )
            Column {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = role,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                )
            }
        }
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            shape = MaterialTheme.shapes.large.copy(
                bottomStart = CornerSize(0.dp),
                bottomEnd = CornerSize(0.dp),
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                content()
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                )
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = onDeleteAccount,
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = stringResource(R.string.profile_delete_account),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
