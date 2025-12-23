package com.example.mathapp.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProfileField(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
) {
    ProfileSection(
        modifier = modifier,
        title = title,
        isMain = false,
    ) {
        Text(
            text = value,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    title: String,
    isMain: Boolean = true,
    painter: Painter? = null,
    content: @Composable () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top,
    ) {
        if (isMain && painter != null) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painter,
                contentDescription = null,
            )
        }
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                space = if (isMain) 8.dp else 4.dp,
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = title,
                fontSize = if (isMain) 16.sp else 14.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
            )
            content()
        }
    }
}
