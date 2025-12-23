package com.example.mathapp.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathapp.R
import com.example.mathapp.presentation.profile.ProfileState.Success.UserInfo.Parent
import com.example.mathapp.ui.common.DropDownBox

@Composable
fun ProfileParentContent(
    parent: Parent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ProfileSection(
            title = stringResource(R.string.phone_number),
            painter = rememberVectorPainter(Icons.Default.Call),
        ) {
            Text(
                text = parent.phone,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
            )
        }
        ProfileSection(
            title = stringResource(R.string.my_children),
            painter = painterResource(R.drawable.ic_group),
        ) {
            parent.children.forEach { child ->
                DropDownBox(title = child.name) {
                    ProfileChildContent(child = child, isMain = true)
                }
            }
        }
    }
}
