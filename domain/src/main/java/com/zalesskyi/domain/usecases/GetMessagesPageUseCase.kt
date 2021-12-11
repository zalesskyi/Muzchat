package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.models.Message
import com.zalesskyi.domain.repositories.MessagesRepository
import com.zalesskyi.domain.usecases.base.UseCaseCoroutine
import javax.inject.Inject

class GetMessagesPageUseCase
@Inject
constructor(private val repository: MessagesRepository) : UseCaseCoroutine<Int, List<Message>>() {

    override suspend fun executeOnBackground(params: Int): List<Message> =
        repository.getMessages(params)
}