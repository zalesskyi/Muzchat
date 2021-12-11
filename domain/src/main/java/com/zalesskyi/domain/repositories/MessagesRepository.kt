package com.zalesskyi.domain.repositories

import com.zalesskyi.domain.models.Message
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {

    fun observeMessages(): Flow<List<Message>>

    suspend fun saveMessage(message: Message)

    suspend fun getMessages(offset: Int): List<Message>

    suspend fun generateSmartReply(recentMessages: List<Message>): Result<Message>
}