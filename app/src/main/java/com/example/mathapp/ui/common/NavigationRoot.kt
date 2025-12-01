package com.example.mathapp.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mathapp.ui.profile.ProfileScreen
import com.example.mathapp.ui.timetable.TimetableScreen
import kotlinx.serialization.Serializable

object Screens {

    @Serializable
    object Profile

    @Serializable
    object Timetable
}

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Profile) {
        composable<Screens.Profile> {
            ProfileScreen(
                modifier = modifier,
            )
        }
        composable<Screens.Timetable> {
            TimetableScreen(
                modifier = modifier
            )
        }
    }
}
