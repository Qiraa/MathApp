package com.example.mathapp.ui.timetable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mathapp.presentation.timetable.TimetableViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathapp.R
import com.example.mathapp.presentation.timetable.TimetableState
import com.example.mathapp.ui.common.ErrorScreen
import com.example.mathapp.ui.common.LoadingScreen

@Composable
fun TimetableScreen(
    modifier: Modifier = Modifier,
    viewModel: TimetableViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()
    when (val currentState = state) {
        TimetableState.Error -> ErrorScreen(modifier = modifier)
        TimetableState.Loading -> LoadingScreen(modifier = modifier)
        TimetableState.Success -> TODO()
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    userName: String,
    date: DateContent,
    onOpenCalendarClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) {
        Text(
            text = stringResource(R.string.timetable_title, userName),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 24.sp,
        )
        Text(
            text = stringResource(R.string.timetable_description),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.secondary,
        )
        Row {
            Text(
                text = date.day,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
            )
            Button(
                onClick = onOpenCalendarClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = stringResource(R.string.date_picker),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                )
            }
        }
    }
}
