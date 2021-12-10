package com.zalesskyi.muzchat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import javax.inject.Inject

class AppNavComponentsNavigator
@Inject
constructor(
    private val navController: NavController

) : Navigator {

    override fun navigate(command: NavigationCommand, extras: FragmentNavigator.Extras?) {
        realNavigation(command, extras)
    }

    override fun navigate(direction: NavDirections, args: Map<*, *>?) = Unit

    private fun realNavigation(command: NavigationCommand,
                               extras: FragmentNavigator.Extras?) {
        when (command) {
            is NavigationCommand.Back,
            is NavigationCommand.BackTo,
            is NavigationCommand.ToRoot -> Unit

            is NavigationCommand.To -> {
                if (hasCurrentDestinationDirection(command.directions)) {
                    navController.navigate(command.directions)
                }
            }
            is NavigationCommand.ToDirections -> {
                if (hasCurrentDestinationDirection(command.directions.actionId)) {
                    extras?.let {
                        navController.navigate(command.directions, it)
                    } ?: navController.navigate(command.directions)
                }
            }
            is NavigationCommand.ToNewRoot -> {
                if (hasCurrentDestinationDirection(command.directions)) {
                    navController.navigate(command.directions)
                }
            }
        }
    }

    private fun hasCurrentDestinationDirection(actionId: Int): Boolean {
        return navController.currentDestination?.getAction(actionId) != null
    }
}