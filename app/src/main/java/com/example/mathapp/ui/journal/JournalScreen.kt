package com.example.mathapp.ui.journal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mathapp.R
import com.example.mathapp.presentation.journal.JournalState
import com.example.mathapp.presentation.journal.JournalState.Success.JournalClass
import com.example.mathapp.presentation.journal.JournalViewModel
import com.example.mathapp.ui.common.ErrorScreen
import com.example.mathapp.ui.common.LoadingScreen

@Composable
fun JournalScreen(
    modifier: Modifier = Modifier,
    viewModel: JournalViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()

    when (val currentState = state) {
        JournalState.Error ->
            ErrorScreen(modifier = modifier)
        JournalState.Loading ->
            LoadingScreen(modifier = modifier)
        is JournalState.Success ->
            SuccessContent(
                modifier = modifier,
                courses = currentState.courses,
                activeCourseIndex = currentState.activeCourseIndex,
                classes = currentState.classes,
                onCourseChange = viewModel::changeCourse,
                onOpenFile = viewModel::openFile,
            )
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    courses: List<String>,
    activeCourseIndex: Int,
    classes: List<JournalClass>,
    onCourseChange: (index: Int) -> Unit,
    onOpenFile: (file: String) -> Unit,
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 0.dp) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
                .padding(top = 16.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 22.dp),
                text = stringResource(R.string.journal_title),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CoursesRow(
                courses = courses,
                activeCourseIndex = activeCourseIndex,
                onCourseChange = onCourseChange,
            )
            CoursePage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = MaterialTheme.colorScheme.background),
                classes = classes,
                onOpenFile = onOpenFile,
            )
        }
    }
}

@Composable
private fun CoursesRow(
    modifier: Modifier = Modifier,
    courses: List<String>,
    activeCourseIndex: Int,
    onCourseChange: (index: Int) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        itemsIndexed(courses) { index, course ->
            Card(
                shape = MaterialTheme.shapes.medium
                    .copy(
                        bottomEnd = CornerSize(0.dp),
                        bottomStart = CornerSize(0.dp),
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = if (index == activeCourseIndex) {
                        MaterialTheme.colorScheme.background
                    } else {
                        MaterialTheme.colorScheme.surfaceContainer
                    },
                ),
                onClick = { onCourseChange(index) },
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    text = course,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}

@Composable
private fun CoursePage(
    modifier: Modifier = Modifier,
    classes: List<JournalClass>,
    onOpenFile: (file: String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillParentMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = stringResource(R.string.journal_class_number))
                    Text(text = stringResource(R.string.journal_class_date))
                    Text(text = stringResource(R.string.journal_class_visit))
                    Text(text = stringResource(R.string.journal_class_score))
                }
                HorizontalDivider(
                    modifier = Modifier.fillParentMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    thickness = 1.dp,
                )
            }
        }
        itemsIndexed(classes) { index, journalClass ->
            ClassCard(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .wrapContentHeight(),
                number = index + 1,
                journalClass = journalClass,
                onOpenFile = onOpenFile,
            )
        }
    }
}

