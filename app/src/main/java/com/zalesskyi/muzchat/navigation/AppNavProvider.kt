package com.zalesskyi.muzchat.navigation

import androidx.navigation.NavController

interface AppNavProvider {

    fun getNavController(): NavController
}