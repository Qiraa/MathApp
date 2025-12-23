package com.example.mathapp.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathapp.R
import com.example.mathapp.presentation.profile.ProfileState.Success.UserInfo.Child
import com.example.mathapp.ui.common.DropDownBox


@Composable
fun ProfileChildContent(
    modifier: Modifier = Modifier,
    child: Child,
    isMain: Boolean,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ProfileSection(
            title = stringResource(R.string.phone_number),
            painter = rememberVectorPainter(Icons.Default.Call),
            isMain = !isMain,
        ) {
            Text(
                text = child.phone,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
            )
        }
        ProfileSection(
            title = stringResource(R.string.courses),
            painter = rememberVectorPainter(Icons.Default.Edit),
            isMain = !isMain,
        ) {
            Column {
                child.courses.forEach { course ->
                    DropDownBox(
                        title = course.subject,
                        content = {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                ProfileField(
                                    title = stringResource(R.string.profile_course_time),
                                    value = course.time,
                                )
                                ProfileField(
                                    title = stringResource(R.string.profile_course_address),
                                    value = course.address,
                                )
                                ProfileField(
                                    title = stringResource(R.string.profile_course_professors),
                                    value = course.professors.joinToString(separator = "\n"),
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}
