package com.zalesskyi.data.remote.di

import com.zalesskyi.data.remote.MessagesRemoteService
import com.zalesskyi.data.remote.MessagesRemoteServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteServiceModule {

    @Binds
    abstract fun bindMessagesRemoteService(impl: MessagesRemoteServiceImpl): MessagesRemoteService
}