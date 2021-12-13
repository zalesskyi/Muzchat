package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.models.CURRENT_USER_ID
import com.zalesskyi.domain.models.MessageModel
import com.zalesskyi.domain.models.generateMessageId
import com.zalesskyi.domain.repositories.MessagesRepository
import com.zalesskyi.domain.usecases.base.UseCaseCoroutine
import javax.inject.Inject

class SendMessageUseCase
@Inject
constructor(private val repository: MessagesRepository) : UseCaseCoroutine<String, Unit>() {

    override suspend fun executeOnBackground(params: String) {
        val message = MessageModel(
            id = generateMessageId(),
            createdAt = System.currentTimeMillis(),
            body = params,
            senderId = CURRENT_USER_ID
        )
        repository.saveMessage(message)
    }
}