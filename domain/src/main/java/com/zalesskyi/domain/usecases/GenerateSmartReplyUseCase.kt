package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.models.CURRENT_USER_ID
import com.zalesskyi.domain.models.Displayable
import com.zalesskyi.domain.models.MessageModel
import com.zalesskyi.domain.models.errors.NoNeedToGenerateMessageException
import com.zalesskyi.domain.repositories.MessagesRepository
import com.zalesskyi.domain.usecases.base.UseCaseCoroutine
import javax.inject.Inject

class GenerateSmartReplyUseCase
@Inject
constructor(private val repository: MessagesRepository) : UseCaseCoroutine<List<Displayable>, Result<MessageModel>>() {

    override suspend fun executeOnBackground(params: List<Displayable>): Result<MessageModel> {
        val messages = params.filterIsInstance<MessageModel>()
        return messages.lastOrNull()?.takeIf { it.senderId == CURRENT_USER_ID }?.let { _ ->
            repository.generateSmartReply(messages).onSuccess {
                repository.saveMessage(it)
            }
        } ?: Result.failure(NoNeedToGenerateMessageException())
    }
}