package com.example.mathapp.ui.journal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.mathapp.R
import com.example.mathapp.presentation.journal.JournalState.Success.JournalClass

@Composable
fun ClassCard(
    modifier: Modifier = Modifier,
    number: Int,
    journalClass: JournalClass,
    onOpenFile: (file: String) -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "$number.")
                Text(text = journalClass.date)
                Checkbox(
                    modifier = Modifier.padding(horizontal = 40.dp),
                    checked = journalClass.visited,
                    onCheckedChange = null,
                )
                Text(
                    textDecoration = TextDecoration.Underline,
                    text = journalClass.score?.toString() ?: "  ",
                )
            }
            if (journalClass.homework != null) {
                ClassHomeworkCard(
                    homework = journalClass.homework,
                    onOpenFile = onOpenFile,
                )
            }
        }
    }
}

@Composable
private fun ClassHomeworkCard(
    modifier: Modifier = Modifier,
    homework: JournalClass.Homework,
    onOpenFile: (file: String) -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(text = homework.description)
            if (homework.files.isNotEmpty()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    homework.files.forEach { file ->
                        Card(
                            modifier = Modifier.wrapContentWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            ),
                            onClick = { onOpenFile(file) },
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                            ) {
                                Text(text = file)
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_file),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
