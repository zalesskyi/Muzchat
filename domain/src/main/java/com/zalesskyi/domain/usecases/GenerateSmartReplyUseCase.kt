package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.models.CURRENT_USER_ID
import com.zalesskyi.domain.models.Message
import com.zalesskyi.domain.models.errors.NoNeedToGenerateMessageException
import com.zalesskyi.domain.repositories.MessagesRepository
import com.zalesskyi.domain.usecases.base.UseCaseCoroutine
import javax.inject.Inject

class GenerateSmartReplyUseCase
@Inject
constructor(private val repository: MessagesRepository) : UseCaseCoroutine<List<Message>, Result<Message>>() {

    override suspend fun executeOnBackground(params: List<Message>): Result<Message> {
        return params.lastOrNull()?.takeIf { it.senderId == CURRENT_USER_ID }?.let { _ ->
            repository.generateSmartReply(params).onSuccess {
                repository.saveMessage(it)
            }
        } ?: Result.failure(NoNeedToGenerateMessageException())
    }
}