package com.zalesskyi.data.repositories.di

import com.zalesskyi.data.repositories.MessagesRepositoryImpl
import com.zalesskyi.domain.repositories.MessagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMessagesRepository(repository: MessagesRepositoryImpl): MessagesRepository
}