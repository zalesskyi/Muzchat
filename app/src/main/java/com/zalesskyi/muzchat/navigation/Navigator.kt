package com.zalesskyi.muzchat.navigation

import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator

interface Navigator {

    fun navigate(command: NavigationCommand,
                 extras: FragmentNavigator.Extras? = null)

    fun navigate(direction: NavDirections, args: Map<*, *>? = null)
}