package com.example.mathapp.ui.common.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mathapp.ui.journal.JournalScreen
import com.example.mathapp.ui.notifications.NotificationsScreen
import com.example.mathapp.ui.profile.ProfileScreen
import com.example.mathapp.ui.timetable.TimetableScreen

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = NavigationScreen.Timetable

    val backStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            val currentGroup = NavigationGroup.entries.find { group ->
                group.screenClasses.any { screenClass ->
                    backStackEntry?.destination?.route == screenClass.route()
                }
            }

            NavigationBar(
                currentGroup = currentGroup ?: NavigationGroup.MAIN,
                onOpenScreen = { screen ->
                    navController.navigate(screen) {
                        popUpTo(startDestination)
                    }
                }
            )
        }
    ) { contentPadding ->
        NavHost(navController = navController, startDestination = startDestination) {
            composable<NavigationScreen.Timetable> {
                TimetableScreen(
                    modifier = modifier.padding(contentPadding)
                )
            }
            composable<NavigationScreen.Journal> {
                JournalScreen(
                    modifier = modifier.padding(contentPadding)
                )
            }
            composable<NavigationScreen.Notifications> {
                NotificationsScreen(
                    modifier = modifier.padding(contentPadding)
                )
            }
            composable<NavigationScreen.Profile> {
                ProfileScreen(
                    modifier = modifier.padding(contentPadding),
                )
            }
        }
    }
}

@Composable
private fun NavigationBar(
    modifier: Modifier = Modifier,
    currentGroup: NavigationGroup,
    onOpenScreen: (NavigationScreen) -> Unit,
) {
    NavigationBar(modifier = modifier) {
        NavigationGroup.entries.forEach { group ->
            val isSelected = group == currentGroup
            val assets = group.assets()
            val iconId = if (isSelected) assets.selectedIconId else assets.unselectedIconId

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    val screen = when (group) {
                        NavigationGroup.MAIN -> NavigationScreen.Timetable
                        NavigationGroup.JOURNAL -> NavigationScreen.Journal
                        NavigationGroup.NOTIFICATIONS -> NavigationScreen.Notifications
                        NavigationGroup.PROFILE -> NavigationScreen.Profile
                    }
                    onOpenScreen(screen)
                },
                icon = {
                    Icon(
                        painter = painterResource(iconId),
                        contentDescription = null,
                    )
                },
                label = { Text(stringResource(assets.titleId)) }
            )
        }
    }
}
