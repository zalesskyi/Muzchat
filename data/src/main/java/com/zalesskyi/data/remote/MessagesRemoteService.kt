package com.zalesskyi.data.remote

import com.google.mlkit.nl.smartreply.SmartReply
import com.google.mlkit.nl.smartreply.TextMessage
import com.zalesskyi.domain.models.ANOTHER_USER_ID
import com.zalesskyi.domain.models.CURRENT_USER_ID
import com.zalesskyi.domain.models.MessageModel
import com.zalesskyi.domain.models.errors.NoMessageToReplyException
import com.zalesskyi.domain.models.generateMessageId
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface MessagesRemoteService {

    suspend fun generateSmartReply(previousMessages: List<MessageModel>): Result<MessageModel>
}

class MessagesRemoteServiceImpl
@Inject
constructor() : MessagesRemoteService {

    override suspend fun generateSmartReply(previousMessages: List<MessageModel>): Result<MessageModel> =
        suspendCoroutine { continuation ->
        val conversation = previousMessages.map(::convertToMlMessage)
        SmartReply.getClient().suggestReplies(conversation)
            .addOnSuccessListener { result ->
                result.suggestions.firstOrNull()?.let {
                    val message = MessageModel(generateMessageId(),
                        System.currentTimeMillis(),
                        it.text,
                        ANOTHER_USER_ID)
                    continuation.resume(Result.success(message))
                } ?: continuation.resume(Result.failure(NoMessageToReplyException()))
            }.addOnFailureListener {
                continuation.resume(Result.failure(NoMessageToReplyException()))
            }
    }

    private fun convertToMlMessage(message: MessageModel) =
        if (message.senderId == CURRENT_USER_ID)
            TextMessage.createForLocalUser(message.body, message.createdAt)
        else
            TextMessage.createForRemoteUser(message.body, message.createdAt, message.senderId.toString())
}