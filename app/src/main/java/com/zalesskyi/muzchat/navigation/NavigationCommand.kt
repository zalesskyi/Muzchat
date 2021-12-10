package com.zalesskyi.muzchat.navigation

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class ToDirections(val directions: NavDirections) : NavigationCommand()
    data class To(val directions: Int, val data: Map<*, *>? = null) : NavigationCommand()
    object Back : NavigationCommand()
    data class BackTo(val destinationId: Int) : NavigationCommand()
    object ToRoot : NavigationCommand()
    data class ToNewRoot(val directions: Int, val data: Map<*, *>? = null) : NavigationCommand()
}