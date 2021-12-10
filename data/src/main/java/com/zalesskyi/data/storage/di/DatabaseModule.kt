package com.zalesskyi.data.storage.di

import android.content.Context
import androidx.room.Room
import com.zalesskyi.data.storage.AppDatabase
import com.zalesskyi.data.storage.dao.MessagesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "db")
            .build()

    @Singleton
    @Provides
    fun provideMessagesDao(db: AppDatabase): MessagesDao = db.messagesDao()
}