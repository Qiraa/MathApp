package com.example.mathapp.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mathapp.R

@Composable
fun DropDownBox(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit,
) {
    var isOpened by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top,
    ) {
        IconButton(
            onClick = { isOpened = !isOpened }
        ) {
            val iconRotation = animateFloatAsState(targetValue = if (isOpened) 90f else 0f)

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .rotate(iconRotation.value),
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = stringResource(R.string.open_content_description),
            )
        }
        Column(
            modifier = Modifier.padding(top = 12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(text = title)
            AnimatedVisibility(visible = isOpened) {
                content()
            }
        }
    }
}
