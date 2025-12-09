package com.example.mathapp.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mathapp.data.notification.NotificationRepository
import com.example.mathapp.di.Di
import com.example.mathapp.ui.common.navigation.NavigationGroup
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RootViewModel(
    notificationRepository: NotificationRepository = Di.notificationRepository,
) : ViewModel() {

    val state: StateFlow<RootState> = notificationRepository.haveNotifications()
        .map { hasNotifications ->
            val badgeGroups = setOfNotNull(
                if (hasNotifications) NavigationGroup.NOTIFICATIONS else null,
            )
            RootState(badgeGroups)
        }.stateIn(viewModelScope, SharingStarted.Lazily, RootState(emptySet()))
}
