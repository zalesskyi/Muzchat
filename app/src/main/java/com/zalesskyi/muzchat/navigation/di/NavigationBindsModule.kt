package com.zalesskyi.muzchat.navigation.di

import com.zalesskyi.muzchat.navigation.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationBindsModule {

    @Binds
    abstract fun bindNavigator(navigator: AppNavComponentsNavigator): Navigator
}