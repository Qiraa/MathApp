package com.example.mathapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar() }
                ) { innerPadding ->
                    ProfileScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Исправленная нижняя панель с Email
@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Filled.Home, contentDescription = null) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Filled.Email, contentDescription = null) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Filled.Person, contentDescription = null) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Filled.Notifications, contentDescription = null) }
        )
    }
}

// Обновлённый профиль с параметром modifier
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Avatar",
                tint = Color.Gray,
                modifier = Modifier.size(110.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Профиль", fontWeight = FontWeight.Bold, fontSize = 26.sp)
        Spacer(modifier = Modifier.height(24.dp))

        ProfileItem(
            icon = Icons.Filled.Person,
            label = "ФИО",
            value = "Иван Иванов Иванов",
            labelColor = Color(0xFFD56263)
        )
        ProfileItem(
            icon = Icons.Filled.DateRange,
            label = "Дата рождения",
            value = "01.01.2000",
            labelColor = Color(0xFF7897B1)
        )
        ProfileItem(
            icon = Icons.Filled.Home,
            label = "Домашний адрес",
            value = "ул. Пушкина, дом 1, кв. 12",
            labelColor = Color(0xFF87A889)
        )
        ProfileItem(
            icon = Icons.Filled.Phone,
            label = "Номер телефона",
            value = "+7 908 122 3232\n+7 908 655 6759",
            labelColor = Color(0xFFA78CB5)
        )
        ProfileItem(
            icon = Icons.Filled.Email,
            label = "Электронная почта",
            value = "Ivanov@yandex.ru",
            labelColor = Color(0xFF87A889)
        )
    }
}

@Composable
fun ProfileItem(
    icon: ImageVector,
    label: String,
    value: String,
    labelColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = labelColor,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(label, color = labelColor, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(value, fontSize = 15.sp, color = Color.Black)
        }
    }
}
