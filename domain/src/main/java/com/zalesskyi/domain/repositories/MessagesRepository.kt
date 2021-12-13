package com.zalesskyi.domain.repositories

import com.zalesskyi.domain.models.MessageModel
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {

    fun observeMessages(): Flow<List<MessageModel>>

    fun saveMessage(message: MessageModel)

    suspend fun generateSmartReply(recentMessages: List<MessageModel>): Result<MessageModel>
}