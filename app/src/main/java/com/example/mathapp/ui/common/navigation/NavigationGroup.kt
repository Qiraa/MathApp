package com.example.mathapp.ui.common.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.example.mathapp.R
import kotlinx.serialization.Serializable

@Keep
@Serializable
enum class NavigationGroup(vararg val screenClasses: Class<out NavigationScreen>) {
    MAIN(
        NavigationScreen.Timetable::class.java,
    ),
    JOURNAL(
        NavigationScreen.Journal::class.java,
    ),
    NOTIFICATIONS(
        NavigationScreen.Notifications::class.java,
    ),
    PROFILE(
        NavigationScreen.Profile::class.java,
    ),
}

data class NotificationGroupAssets(
    @StringRes val titleId: Int,
    @DrawableRes val selectedIconId: Int,
    @DrawableRes val unselectedIconId: Int,
)

@Composable
fun NavigationGroup.assets(): NotificationGroupAssets {
    return when (this) {
        NavigationGroup.MAIN ->
            NotificationGroupAssets(
                titleId = R.string.navigation_bar_main,
                selectedIconId = R.drawable.ic_main_selected,
                unselectedIconId = R.drawable.ic_main_unselected,
            )
        NavigationGroup.JOURNAL ->
            NotificationGroupAssets(
                titleId = R.string.navigation_bar_journal,
                selectedIconId = R.drawable.ic_journal_selected,
                unselectedIconId = R.drawable.ic_journal_unselected,
            )
        NavigationGroup.NOTIFICATIONS ->
            NotificationGroupAssets(
                titleId = R.string.navigation_bar_notifications,
                selectedIconId = R.drawable.ic_notifications_selected,
                unselectedIconId = R.drawable.ic_notifications_unselected,
            )
        NavigationGroup.PROFILE ->
            NotificationGroupAssets(
                titleId = R.string.navigation_bar_profile,
                selectedIconId = R.drawable.ic_profile_selected,
                unselectedIconId = R.drawable.ic_profile_unselected,
            )
    }
}
