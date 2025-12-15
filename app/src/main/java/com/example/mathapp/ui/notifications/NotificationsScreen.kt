package com.example.mathapp.ui.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mathapp.R
import com.example.mathapp.presentation.notification.NotificationState
import com.example.mathapp.presentation.notification.NotificationViewModel
import com.example.mathapp.ui.common.ErrorScreen
import com.example.mathapp.ui.common.LoadingScreen

@Composable
fun NotificationsScreen(
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()
    when(val currentState = state) {
        NotificationState.Error ->
            ErrorScreen(modifier = modifier)
        NotificationState.Loading ->
            LoadingScreen(modifier = modifier)
        is NotificationState.Success ->
            SuccessContent(
                modifier = modifier,
                notifications = currentState.notifications,
                onRemoveNotification = viewModel::onRemoveNotification,
            )
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    notifications: List<NotificationState.Success.Notification>,
    onRemoveNotification: (id: Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(44.dp),
    ){
        Text(
            modifier = Modifier.padding(top = 22.dp),
            text = stringResource(R.string.notification_title),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            items(
                items = notifications,
                key = { it.id },
            ) { notification ->
                val state = rememberSwipeToDismissBoxState(
                    confirmValueChange = {
                        if (it == SwipeToDismissBoxValue.StartToEnd) {
                            onRemoveNotification(notification.id)
                            false
                        } else {
                            true
                        }
                    }
                )
                SwipeToDismissBox(
                    modifier = Modifier.animateItem(),
                    state = state,
                    backgroundContent = { },
                    content = {
                        NotificationCard(
                            title = notification.title,
                            data = notification.date,
                            onDeleteNotification = { onRemoveNotification(notification.id) },
                        )
                    }
                )
            }
        }
    }
}
