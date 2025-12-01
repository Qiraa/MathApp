package com.example.mathapp.ui.common.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavigationScreen {

    @Serializable
    data object Profile : NavigationScreen

    @Serializable
    data object Timetable : NavigationScreen

    @Serializable
    data object Journal : NavigationScreen

    @Serializable
    data object Notifications : NavigationScreen
}

fun Class<out NavigationScreen>.route(): String {
    return this.name.replace("$", ".")
}
