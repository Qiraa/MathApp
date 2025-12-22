package com.example.mathapp.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathapp.R

@Composable
fun StudentSuccessContent(
    modifier: Modifier = Modifier,
    imageId: Int?,
    name: String,
    role: UserRole,
    phone: String,
    course: List<Course>,

    ) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.profile_title),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (imageId != null) {
                Image(
                    painter = painterResource(imageId),
                    contentDescription = null,
                )
            } else {
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
            }
            Column {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = role.toReadableString(),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                )
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Image(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                    )
                    Text(
                        text = stringResource(R.string.phone_number),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Text(
                    text = phone,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Image(
                        imageVector = Icons.Default.Create,
                        contentDescription = null,
                    )
                    Text(
                        text = stringResource(R.string.courses),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
private fun CourseRow(
    course: List<Course>
) {

}
