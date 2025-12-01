package com.example.mathapp.ui.timetable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
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
        TimetableState.Success -> SuccessContent(
            modifier = modifier,
            userName = "Daniil",
            date = DateContent(day = "12", weekDay = "Monday", month = "December", year = "2025"),
            onOpenCalendarClick = {},
        )
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
            .padding(16.dp)
            .systemBarsPadding(),
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
        Row {
            Text(
                text = date.day,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
            )
            Column {
                Text(
                    text = date.weekDay,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = date.month,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )
            }
            Button(
                onClick = onOpenCalendarClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = stringResource(R.string.date_picker),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        HorizontalDivider(modifier = Modifier.height(16.dp))
        TimetableItem(
            startTime = "12:00",
            endTime = "13:00",
            subject = "Математика",
            teacherName = "Рябова Алевтина Витальевна",
            placeName = "4 к, ул. Нефтезаводская 11, ауд. 201",
            onOpenMap = {}
        )
        TimetableItem(
            startTime = "12:00",
            endTime = "13:00",
            subject = "Математика",
            teacherName = "Рябова Алевтина Витальевна",
            placeName = "4 к, ул. Нефтезаводская 11, ауд. 201",
            onOpenMap = {}
        )
        TimetableItem(
            startTime = "12:00",
            endTime = "13:00",
            subject = "Математика",
            teacherName = "Рябова Алевтина Витальевна",
            placeName = "4 к, ул. Нефтезаводская 11, ауд. 201",
            onOpenMap = {}
        )
    }
}
