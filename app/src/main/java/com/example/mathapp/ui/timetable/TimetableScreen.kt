package com.example.mathapp.ui.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mathapp.R
import com.example.mathapp.presentation.timetable.TimetableEffect
import com.example.mathapp.presentation.timetable.TimetableState
import com.example.mathapp.presentation.timetable.TimetableViewModel
import com.example.mathapp.ui.common.ErrorScreen
import com.example.mathapp.ui.common.LoadingScreen
import com.example.mathapp.ui.common.launchViewIntent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableScreen(
    modifier: Modifier = Modifier,
    viewModel: TimetableViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val datePickerState = rememberDatePickerState()

    LaunchedEffect(datePickerState.selectedDateMillis) {
        viewModel.updateDate(datePickerState.selectedDateMillis)
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TimetableEffect.OpenViewIntent -> context.launchViewIntent(effect.url)
            }
        }
    }

    when (val currentState = state) {
        TimetableState.Error -> ErrorScreen(modifier = modifier)
        TimetableState.Loading -> LoadingScreen(modifier = modifier)
        is TimetableState.Success -> {
            if (currentState.showDatePicker) {
                Dialog(onDismissRequest = viewModel::hideDatePicker) {
                    DatePicker(
                        modifier = Modifier.shadow(elevation = 4.dp),
                        state = datePickerState,
                        showModeToggle = false,
                    )
                }
            }

            SuccessContent(
                modifier = modifier,
                userName = currentState.userName,
                date = currentState.date,
                courses = currentState.courses,
                onOpenMap = viewModel::openAddress,
                onOpenCalendarClick = viewModel::showDatePicker,
            )
        }
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    userName: String,
    date: String,
    courses: List<TimetableState.Success.Course>,
    onOpenMap: (address: String) -> Unit,
    onOpenCalendarClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = stringResource(R.string.timetable_title, userName),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 22.sp,
            textAlign = TextAlign.Start,
        )
        Text(
            text = stringResource(R.string.timetable_description),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.secondary,
        )
        Row(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .clickable(onClick = onOpenCalendarClick)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium,
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.secondary.copy(0.7f),
                    shape = MaterialTheme.shapes.medium,
                )
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = date,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Icon(
                painter = painterResource(R.drawable.ic_calendar),
                contentDescription = null,
            )
        }
        HorizontalDivider(modifier = Modifier.height(4.dp))
        LazyColumn {
            item {
                TimetableHeader()
            }
            items(courses) { course ->
                TimetableItem(
                    startTime = course.startTime,
                    endTime = course.endTime,
                    subject = course.subject,
                    professorName = course.professorName,
                    address = course.address,
                    onOpenMap = { onOpenMap(course.address) },
                )
            }
        }
    }
}

@Preview
@Composable
private fun SuccessScreen() {

}
