package com.zalesskyi.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zalesskyi.data.storage.dao.MessagesDao
import com.zalesskyi.data.storage.models.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun messagesDao(): MessagesDao
}