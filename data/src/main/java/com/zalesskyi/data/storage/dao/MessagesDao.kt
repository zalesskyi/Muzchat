package com.zalesskyi.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zalesskyi.data.storage.models.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMessage(message: MessageEntity)

    @Insert
    fun insertAll(messages: List<MessageEntity>)

    @Query("SELECT * FROM Messages ORDER BY createdAt")
    fun observeLastMessages(): Flow<List<MessageEntity>>
}