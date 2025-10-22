package com.example.myapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme { // Material3 тема
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = { CustomBottomBar() },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            SearchBar()
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Добро пожаловать,\n[имя_пользователя]!",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
            CardGrid(modifier = Modifier.padding(horizontal = 12.dp))
        }
    }
}

@Composable
fun SearchBar() {
    Box(
        modifier = Modifier
            .padding(top = 16.dp, start = 12.dp, end = 12.dp)
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFF5F4F5))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Canvas(modifier = Modifier.size(22.dp).padding(start = 12.dp)) {
                drawCircle(Color.LightGray, radius = size.minDimension / 2)
            }
            Text(
                text = "Поиск",
                color = Color.Gray,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}

@Composable
fun CardGrid(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            CardBox(bgColor = Color(0xFF98B7E8), text = "[Текст]", iconType = 0)
            CardBox(bgColor = Color(0xFFAD95CA), text = "[Текст]", iconType = 1)
        }
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            CardBox(bgColor = Color(0xFFA5CC98), text = "[Текст]", iconType = 2)
            CardBox(bgColor = Color(0xFFE68C8C), text = "[Текст]", iconType = 3)
        }
    }
}

@Composable
fun RowScope.CardBox(bgColor: Color, text: String, iconType: Int) {
    Box(
        modifier = Modifier
            .weight(1f)
            .aspectRatio(1.25f)
            .clip(RoundedCornerShape(14.dp))
            .background(bgColor)
    ) {
        when (iconType) {
            0 -> Canvas(
                modifier = Modifier
                    .size(38.dp)
                    .align(Alignment.TopStart)
                    .padding(4.dp)
            ) {
                drawCircle(Color.White)
                drawCircle(Color(0xFF98B7E8), radius = 10.dp.toPx(), center = center)
            }

            1 -> Canvas(
                modifier = Modifier
                    .size(38.dp)
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
            ) {
                drawCircle(Color(0xFF58167D), radius = 12.dp.toPx(), center = center)
            }

            2 -> Canvas(
                modifier = Modifier
                    .size(38.dp)
                    .align(Alignment.BottomStart)
                    .padding(4.dp)
            ) {
                drawCircle(Color(0xFF45853F), radius = 18.dp.toPx(), center = center)
            }

            3 -> Canvas(
                modifier = Modifier
                    .size(38.dp)
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            ) {
                drawCircle(Color.White)
                drawCircle(Color(0xFFD03F4B), radius = 10.dp.toPx(), center = center)
            }
        }

        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun CustomBottomBar() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null, tint = Color(0xFF221880)) },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.DateRange, contentDescription = null, tint = Color(0xFFAD95CA)) },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Notifications, contentDescription = null, tint = Color(0xFF408840)) },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = null, tint = Color(0xFFD03F4B)) },
            selected = false,
            onClick = {}
        )
    }
}
