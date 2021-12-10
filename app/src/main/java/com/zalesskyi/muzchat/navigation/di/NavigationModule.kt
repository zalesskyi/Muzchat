package com.zalesskyi.muzchat.navigation.di

import android.app.Activity
import androidx.navigation.NavController
import com.zalesskyi.muzchat.MainActivity
import com.zalesskyi.muzchat.navigation.AppNavComponentsNavigator
import com.zalesskyi.muzchat.navigation.AppNavProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NavigationModule {

    @Provides
    fun provideAppNavProvider(activity: Activity): AppNavProvider = (activity as MainActivity)

    @Provides
    fun provideNavController(provider: AppNavProvider): NavController = provider.getNavController()

    @Provides
    fun provideAppNavigator(navController: NavController): AppNavComponentsNavigator =
        AppNavComponentsNavigator(navController)
}