package com.zalesskyi.data.repositories

import com.zalesskyi.data.mappers.toData
import com.zalesskyi.data.mappers.toDomain
import com.zalesskyi.data.remote.MessagesRemoteService
import com.zalesskyi.data.storage.dao.MessagesDao
import com.zalesskyi.domain.models.MessageModel
import com.zalesskyi.domain.repositories.MessagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MessagesRepositoryImpl
@Inject
constructor(private val dao: MessagesDao,
            private val remoteService: MessagesRemoteService) : MessagesRepository {

    override fun observeMessages(): Flow<List<MessageModel>> =
        dao.observeLastMessages().map { messages ->
            messages.map { it.toDomain() }
        }

    override fun saveMessage(message: MessageModel) {
        dao.addMessage(message.toData())
    }

    override suspend fun generateSmartReply(recentMessages: List<MessageModel>): Result<MessageModel> =
        remoteService.generateSmartReply(recentMessages)
}