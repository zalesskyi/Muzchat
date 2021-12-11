package com.zalesskyi.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zalesskyi.data.storage.models.MessageEntity
import kotlinx.coroutines.flow.Flow

const val DEFAULT_LIMIT = 20

@Dao
interface MessagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMessage(message: MessageEntity)

    @Query("SELECT * FROM Messages ORDER BY createdAt LIMIT :limit OFFSET :offset")
    suspend fun getMessages(offset: Int, limit: Int = DEFAULT_LIMIT): List<MessageEntity>

    @Query("SELECT * FROM Messages ORDER BY createdAt LIMIT $DEFAULT_LIMIT OFFSET 0")
    fun observeLastMessages(): Flow<List<MessageEntity>>
}